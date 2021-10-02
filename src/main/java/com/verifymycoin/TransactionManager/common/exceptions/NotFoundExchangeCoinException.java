package com.verifymycoin.TransactionManager.common.exceptions;

import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.NOT_FOUND_EXCHANGE_COIN;

import lombok.Getter;

@Getter
public class NotFoundExchangeCoinException extends RuntimeException {

    private final Integer code;
    private final String message;

    public NotFoundExchangeCoinException() {
        this.code = NOT_FOUND_EXCHANGE_COIN.getCode();
        this.message = NOT_FOUND_EXCHANGE_COIN.getDescription();
    }
}
