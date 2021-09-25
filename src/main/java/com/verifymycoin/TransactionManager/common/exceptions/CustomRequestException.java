package com.verifymycoin.TransactionManager.common.exceptions;

import com.verifymycoin.TransactionManager.common.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomRequestException {

    private Integer code;
    private String message;

    public CustomRequestException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomRequestException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getDescription();
    }
}
