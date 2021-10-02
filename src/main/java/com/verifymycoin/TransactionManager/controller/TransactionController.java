package com.verifymycoin.TransactionManager.controller;

import static com.verifymycoin.TransactionManager.utils.ApiUtils.success;

import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
import com.verifymycoin.TransactionManager.model.response.CoinExchangeRes;
import com.verifymycoin.TransactionManager.model.response.PaymentCurrencyRes;
import com.verifymycoin.TransactionManager.model.response.TransactionInfoRes;
import com.verifymycoin.TransactionManager.service.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/exchange")
    public ResponseEntity<?> getExchange() {
        return ResponseEntity.ok().body(success(transactionService.getExchanges()));
    }

    @GetMapping("/exchange/{exchangeId}/payment-currency")
    public ResponseEntity<?> getPaymentCurrencyByExchangeId(@PathVariable("exchangeId") final Integer exchangeId) {
        List<PaymentCurrencyRes> res = transactionService.getPaymentCurrencyByExchangeId(exchangeId);
        return ResponseEntity.ok().body(success(res));
    }

    @GetMapping("/exchange/{exchangeId}/coin")
    public ResponseEntity<?> getCoinListByExchangeId(@PathVariable("exchangeId") final Integer exchangeId) {
        List<CoinExchangeRes> res = transactionService.getCoinListByExchangeId(exchangeId);
        return ResponseEntity.ok().body(success(res));
    }

    @PostMapping("/exchange/{exchangeId}")
    public ResponseEntity<?> getTransactions(@PathVariable("exchangeId") Integer exchangeId,
        @RequestBody final TransactionsReq req, @RequestHeader("userId") final String userId) throws Exception {
        log.debug("user_id ====> {}", userId);

        TransactionInfoRes res = transactionService.getTransactionInfoSummary(req, exchangeId, userId);
        return ResponseEntity.ok().body(success(res));
    }
}
