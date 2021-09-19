package com.verifymycoin.TransactionManager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchGb {

    ALL(0, "전체"), BUY(1, "매수 완료"), SELL(2, "매도 완료"), WITHDRAW_CONTINUE(3, "출금 중"),
    DEPOSIT_COMPLETED(4, "입금"), WITHDRAW_COMPLETED(5, "출금"), DEPOSIT_CONTINUE(9, "KRW 입금 중");

    private int code;
    private String description;
}
