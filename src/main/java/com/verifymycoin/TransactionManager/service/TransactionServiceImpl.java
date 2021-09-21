package com.verifymycoin.TransactionManager.service;

import com.verifymycoin.TransactionManager.model.entity.CoinExchangeAssoc;
import com.verifymycoin.TransactionManager.model.entity.Exchange;
import com.verifymycoin.TransactionManager.model.entity.PaymentCurrencyExchangeAssoc;
import com.verifymycoin.TransactionManager.model.response.CoinExchangeRes;
import com.verifymycoin.TransactionManager.model.response.ExchangeRes;
import com.verifymycoin.TransactionManager.model.response.PaymentCurrencyRes;
import com.verifymycoin.TransactionManager.repository.CoinExchangeAssocRepository;
import com.verifymycoin.TransactionManager.repository.ExchangeRepository;
import com.verifymycoin.TransactionManager.repository.PaymentCurrencyExchangeAssocRepository;
import com.verifymycoin.TransactionManager.utils.BithumbClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BithumbClient bithumbClient;
    private final Environment env;
    private final ExchangeRepository exchangeRepository;
    private final PaymentCurrencyExchangeAssocRepository paymentCurrencyExchangeAssocRepo;
    private final CoinExchangeAssocRepository coinExchangeAssocRepository;
    private final ModelMapper mapper;

    /**
     * Get exchange list
     *
     * @return Exchange list
     */
    @Override
    public List<ExchangeRes> getExchanges() {
        List<ExchangeRes> res = new ArrayList<>();
        Iterable<Exchange> exchanges = exchangeRepository.findAll();
        exchanges.forEach(v -> res.add(mapper.map(v, ExchangeRes.class)));
        return res;
    }

    /**
     * Get payment currency by exchange_id
     *
     * @param exchangeId exchange_id
     * @return Payment currency list by exchange
     */
    @Override
    public List<PaymentCurrencyRes> getPaymentCurrencyByExchangeId(Integer exchangeId) {
        List<PaymentCurrencyRes> res = new ArrayList<>();
        Iterable<PaymentCurrencyExchangeAssoc> paymentCurrencyExchangeAssocs = paymentCurrencyExchangeAssocRepo.findByExchangeId(
            exchangeId);
        paymentCurrencyExchangeAssocs.forEach(v -> res.add(PaymentCurrencyRes.builder()
            .id(v.getId())
            .PaymentCurrencySymbol(v.getPaymentCurrency().getSymbol())
            .paymentCurrencyName(v.getPaymentCurrency().getName())
            .build()));

        return res;
    }

    /**
     * Get coin list by exchange_id
     *
     * @param exchangeId exchange_id
     * @return
     */
    @Override
    public List<CoinExchangeRes> getCoinListByExchangeId(Integer exchangeId) {
        List<CoinExchangeRes> res = new ArrayList<>();
        Iterable<CoinExchangeAssoc> coinExchangeAssocs = coinExchangeAssocRepository.findByExchangeId(exchangeId);

        coinExchangeAssocs.forEach(v -> res.add(CoinExchangeRes.builder()
            .id(v.getId())
            .coinSymbol(v.getCoin().getSymbol())
            .coinName(v.getCoin().getName())
            .build()));
        return res;
    }

    @Override
    public Map<String, String> getTransactions() throws Exception {
        HashMap<String, String> rgParams = new HashMap<>();
        rgParams.put("searchGb", "0");
        rgParams.put("order_currency", "BOA");
        rgParams.put("payment_currency", "KRW");

        try {
            String API_KEY = env.getProperty("key.api-key");
            String API_SECRET = env.getProperty("key.secret-key");

            return bithumbClient.callApi("/info/user_transactions", rgParams, API_KEY, API_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }
}
