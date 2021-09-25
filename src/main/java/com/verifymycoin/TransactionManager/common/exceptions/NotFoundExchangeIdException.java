package com.verifymycoin.TransactionManager.common.exceptions;

import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.NOT_FOUND_EXCHANGE_ID;

import lombok.Getter;

public class NotFoundExchangeIdException extends RuntimeException {

    @Getter
    private final Integer code;

    @Getter
    private final String message;

    public NotFoundExchangeIdException() {
        this.code = NOT_FOUND_EXCHANGE_ID.getCode();
        this.message = NOT_FOUND_EXCHANGE_ID.getDescription();
    }
}
