package com.verifymycoin.TransactionManager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentCurrency {

    KRW("KRW", "원화"), BTC("BTC", "비트코인");

    private String code;
    private String description;
}
