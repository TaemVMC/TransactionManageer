package com.verifymycoin.TransactionManager.utils;

import com.verifymycoin.TransactionManager.model.response.ApiResult;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(1000, "Success", response);
    }
}
