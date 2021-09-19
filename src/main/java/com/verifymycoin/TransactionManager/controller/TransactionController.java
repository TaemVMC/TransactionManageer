package com.verifymycoin.TransactionManager.controller;

import com.verifymycoin.TransactionManager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/transactions")
    public ResponseEntity<?> getTransactions() {
        transactionService.getTransactions();
        return ResponseEntity.ok().body("Success");
    }
}
