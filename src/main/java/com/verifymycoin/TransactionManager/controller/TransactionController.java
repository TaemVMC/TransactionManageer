package com.verifymycoin.TransactionManager.controller;

import com.verifymycoin.TransactionManager.service.TransactionService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction-service")
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
    public ResponseEntity<?> getPaymentCurrencyByExchangeId(@PathVariable("exchangeId") Integer exchangeId) {
        return ResponseEntity.ok().body(transactionService.getPaymentCurrencyByExchangeId(exchangeId));
    }

    @GetMapping("/exchange/{exchangeId}/coin")
    public ResponseEntity<?> getCoinListByExchangeId(@PathVariable("exchangeId") Integer exchangeId) {
        return ResponseEntity.ok().body(transactionService.getCoinListByExchangeId(exchangeId));
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> getTransactions() throws Exception {
        Map<String, String> res = transactionService.getTransactions();
        return ResponseEntity.ok().body(res);
    }
}
