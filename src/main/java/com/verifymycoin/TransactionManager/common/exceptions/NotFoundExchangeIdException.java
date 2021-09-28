package com.verifymycoin.TransactionManager.common.exceptions;

import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.NOT_FOUND_EXCHANGE_ID;

import lombok.Getter;

@Getter
public class NotFoundExchangeIdException extends RuntimeException {

    private final Integer code;
    private final String message;

    public NotFoundExchangeIdException() {
        this.code = NOT_FOUND_EXCHANGE_ID.getCode();
        this.message = NOT_FOUND_EXCHANGE_ID.getDescription();
    }
}
