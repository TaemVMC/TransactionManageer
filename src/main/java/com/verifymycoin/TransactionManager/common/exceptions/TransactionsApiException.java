package com.verifymycoin.TransactionManager.common.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionsApiException extends RuntimeException {

    @Getter
    private Integer code;

    @Getter
    private String message;

    public TransactionsApiException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public TransactionsApiException(String code, String message) {
        this.code = Integer.parseInt(code);
        this.message = message;
    }
}
