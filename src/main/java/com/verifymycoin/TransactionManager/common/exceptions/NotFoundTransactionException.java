package com.verifymycoin.TransactionManager.common.exceptions;

import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.NOT_FOUND_TRANSACTION;

import lombok.Getter;

@Getter
public class NotFoundTransactionException extends RuntimeException {

    private final Integer code;
    private final String message;

    public NotFoundTransactionException() {
        this.code = NOT_FOUND_TRANSACTION.getCode();
        this.message = NOT_FOUND_TRANSACTION.getDescription();
    }
}
