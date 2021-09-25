package com.verifymycoin.TransactionManager.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResult<T> {

    private final int code;
    private final String message;
    private final T data;
}
