package com.verifymycoin.TransactionManager.service;

import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.INTERNAL_SERVER_ERROR;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verifymycoin.TransactionManager.common.enums.SearchGb;
import com.verifymycoin.TransactionManager.common.exceptions.NotFoundExchangeIdException;
import com.verifymycoin.TransactionManager.common.exceptions.NotFoundTransactionException;
import com.verifymycoin.TransactionManager.common.exceptions.TransactionsApiException;
import com.verifymycoin.TransactionManager.model.dto.KafkaTransactionDto;
import com.verifymycoin.TransactionManager.model.dto.TransactionSummaryDto;
import com.verifymycoin.TransactionManager.model.dto.TransactionsDataDto;
import com.verifymycoin.TransactionManager.model.entity.CoinExchangeAssoc;
import com.verifymycoin.TransactionManager.model.entity.Exchange;
import com.verifymycoin.TransactionManager.model.entity.PaymentCurrencyExchangeAssoc;
import com.verifymycoin.TransactionManager.model.entity.TransactionInfo;
import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
import com.verifymycoin.TransactionManager.model.response.CoinExchangeRes;
import com.verifymycoin.TransactionManager.model.response.ExchangeRes;
import com.verifymycoin.TransactionManager.model.response.PaymentCurrencyRes;
import com.verifymycoin.TransactionManager.model.response.TransactionInfoRes;
import com.verifymycoin.TransactionManager.repository.CoinExchangeAssocRepo;
import com.verifymycoin.TransactionManager.repository.ExchangeRepo;
import com.verifymycoin.TransactionManager.repository.PaymentCurrencyExchangeAssocRepo;
import com.verifymycoin.TransactionManager.repository.TransactionInfoRepo;
import com.verifymycoin.TransactionManager.service.kafka.KafkaProducerService;
import com.verifymycoin.TransactionManager.utils.BithumbClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BithumbClient bithumbClient;

    private final ExchangeRepo exchangeRepository;
    private final PaymentCurrencyExchangeAssocRepo paymentCurrencyExchangeAssocRepo;
    private final CoinExchangeAssocRepo coinExchangeAssocRepository;
    private final TransactionInfoRepo transactionInfoRepository;

    private final KafkaProducerService kafkaProducerService;

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    /**
     * Get exchange list
     *
     * @return Exchange list
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRes> getExchanges() {
        List<ExchangeRes> res = new ArrayList<>();
        Iterable<Exchange> exchanges = exchangeRepository.findAll();
        exchanges.forEach(v -> res.add(modelMapper.map(v, ExchangeRes.class)));
        return res;
    }

    /**
     * Get payment currency by exchange_id
     *
     * @param exchangeId exchange_id
     * @return Payment currency list by exchange
     */
    @Override
    @Transactional(readOnly = true)
    public List<PaymentCurrencyRes> getPaymentCurrencyByExchangeId(final Integer exchangeId) {
        List<PaymentCurrencyExchangeAssoc> paymentCurrencyExchangeAssocs = paymentCurrencyExchangeAssocRepo.findByExchangeId(exchangeId);

        if (paymentCurrencyExchangeAssocs.isEmpty()) {
            throw new NotFoundExchangeIdException();
        }

        return paymentCurrencyExchangeAssocs.stream().map(v -> PaymentCurrencyRes.builder()
            .id(v.getId())
            .PaymentCurrencySymbol(v.getPaymentCurrency().getSymbol())
            .paymentCurrencyName(v.getPaymentCurrency().getName())
            .build()).collect(Collectors.toList());
    }

    /**
     * Get coin list by exchange_id
     *
     * @param exchangeId exchange_id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<CoinExchangeRes> getCoinListByExchangeId(final Integer exchangeId) {
        List<CoinExchangeAssoc> coinExchangeAssocs = coinExchangeAssocRepository.findByExchangeId(exchangeId);

        if (coinExchangeAssocs.isEmpty()) {
            throw new NotFoundExchangeIdException();
        }

        return coinExchangeAssocs.stream().map(v -> CoinExchangeRes.builder()
            .id(v.getId())
            .coinSymbol(v.getCoin().getSymbol())
            .coinName(v.getCoin().getName())
            .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionInfoRes getTransactionInfoSummary(final TransactionsReq req, final Integer exchangeId, final String userId)
        throws Exception {
        // 존재하는 거래소인지 체크
        Exchange exchange = exchangeRepository.findById(exchangeId)
            .orElseThrow(NotFoundExchangeIdException::new);

        // 기존 존재하는 데이터인지
        boolean check = transactionInfoRepository.existsTransactionInfo(req, exchangeId, userId);

        // 거래소 서버에서 거래정보 GET 후 저장
        if (!check) {
            getTransactions(req, exchangeId, userId);
        }

        return calcTransactionInfoSummary(req, userId, exchange);
    }

    private TransactionInfoRes calcTransactionInfoSummary(final TransactionsReq req, final String userId, final Exchange exchange)
        throws JsonProcessingException {
        List<TransactionSummaryDto> dto = transactionInfoRepository.calcTransactionSummary(req, exchange.getId(), userId);

        TransactionInfoRes res = new TransactionInfoRes();
        int cnt = 0;
        double buyAmount = 0, sellAmount = 0;

        // API response
        for (TransactionSummaryDto el : dto) {
            if (el.getType().equals(SearchGb.BUY.name())) {
                buyAmount = el.getTotalAmount();
                res.setBuyCount(el.getCount());
            } else {
                sellAmount = el.getTotalAmount();
                res.setSellCount(el.getCount());
            }
            cnt += el.getCount();
        }

        res.setTotalCount(cnt);

        // Kafka
        KafkaTransactionDto kafkaDto = KafkaTransactionDto.builder()
            .exchangeName(exchange.getExchangeName())
            .userId(userId)
            .orderCurrency(req.getOrderCurrency())
            .paymentCurrency(req.getPaymentCurrency().getCode())
            .buyAmount(buyAmount)
            .sellAmount(sellAmount)
            .endDate(req.getEndDate())
            .build();

        kafkaProducerService.send("transactionSummary", kafkaDto);
        return res;
    }

    /**
     * Get transactions from Bithumb
     *
     * @param req
     * @param exchangeId
     * @param userId
     * @throws Exception
     */
    private void getTransactions(final TransactionsReq req, final Integer exchangeId, final String userId) throws Exception {
        // Request params
        Map<String, String> params = objectMapper.convertValue(req, new TypeReference<>() {
        });
        params.put("searchGb", SearchGb.ALL.getCode());
        params.put("count", "50");

        String API_KEY = params.get("api_key");
        String API_SECRET = params.get("secret_key");

        // Request
        Map<String, Object> res = bithumbClient.callApi("/info/user_transactions", params, API_KEY, API_SECRET);
        String status = (String) res.getOrDefault("status", "5900");

        if (status.equals("0000")) {
            List<Object> dataList = (List<Object>) res.getOrDefault("data", new ArrayList<>());

            modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);

            List<TransactionsDataDto> data = dataList.stream()
                .map(v -> modelMapper.map(v, TransactionsDataDto.class))
                .filter(el -> el.getSearch().isSaveType())
                .collect(Collectors.toList());

            if (data.size() == 0) {
                throw new NotFoundTransactionException();
            }

            // 데이터 저장
            saveTransactionInfos(data, exchangeId, userId);
        } else {
            throw new TransactionsApiException(
                (String) res.getOrDefault("status", INTERNAL_SERVER_ERROR.getCode()),
                (String) res.getOrDefault("message", INTERNAL_SERVER_ERROR.getDescription()));
        }
    }

    /**
     * 트랜잭션 정보 저장
     *
     * @param data
     * @param exchangeId
     * @param userId
     */
    private void saveTransactionInfos(final List<TransactionsDataDto> data, final Integer exchangeId,
        final String userId) {
        // dto to entity
        List<TransactionInfo> entity = data.stream()
            .map(v -> new TransactionInfo(v, exchangeId, userId))
            .collect(Collectors.toList());
        transactionInfoRepository.saveAll(entity);
    }
}

