package com.verifymycoin.TransactionManager.controller;

import com.verifymycoin.TransactionManager.model.dto.TransactionsDataDto;
import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
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

    @GetMapping("/heath")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping("/exchange")
    public ResponseEntity<?> getExchange() {
        return ResponseEntity.ok().body(transactionService.getExchanges());
    }

    @GetMapping("/exchange/{exchangeId}/payment-currency")
    public ResponseEntity<?> getPaymentCurrencyByExchangeId(@PathVariable("exchangeId") final Integer exchangeId) {
        return ResponseEntity.ok().body(transactionService.getPaymentCurrencyByExchangeId(exchangeId));
    }

    @GetMapping("/exchange/{exchangeId}/coin")
    public ResponseEntity<?> getCoinListByExchangeId(@PathVariable("exchangeId") final Integer exchangeId) {
        return ResponseEntity.ok().body(transactionService.getCoinListByExchangeId(exchangeId));
    }

    @PostMapping("/exchange/{exchangeId}")
    public ResponseEntity<?> getTransactions(@PathVariable("exchangeId") Integer exchangeId,
        @RequestBody final TransactionsReq req, @RequestHeader("userId") final String userId) throws Exception {
        log.info("user_id ====> {}", userId);

        List<TransactionsDataDto> res = transactionService.getTransactions(req, exchangeId, userId);
        return ResponseEntity.ok().body(res);
    }
}
