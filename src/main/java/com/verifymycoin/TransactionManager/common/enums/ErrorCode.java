package com.verifymycoin.TransactionManager.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_EXCHANGE_ID(1411, "존재하지 않는 거래소 ID입니다."), INTERNAL_SERVER_ERROR(1501, "Unexpected server error");

    private Integer code;
    private String description;
}
