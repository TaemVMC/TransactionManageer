package com.verifymycoin.TransactionManager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TransactionInfoType {

    BUY("매수"), SELL("매도");

    private String description;
}
