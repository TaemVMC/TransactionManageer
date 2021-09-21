package com.verifymycoin.TransactionManager.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentCurrencyRes {

    private int id;

    private String PaymentCurrencySymbol;

    private String paymentCurrencyName;

    @Builder
    public PaymentCurrencyRes(int id, String PaymentCurrencySymbol, String paymentCurrencyName) {
        this.id = id;
        this.PaymentCurrencySymbol = PaymentCurrencySymbol;
        this.paymentCurrencyName = paymentCurrencyName;
    }
}
