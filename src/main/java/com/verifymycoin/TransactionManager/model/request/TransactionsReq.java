package com.verifymycoin.TransactionManager.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.verifymycoin.TransactionManager.common.enums.PaymentCurrency;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionsReq {

    private String apiKey;      // 사용자 API
    private String secretKey;   // 사용자 Secret key

    @JsonProperty("order_currency")
    private String orderCurrency;   // 주문 통화 (코인)

    @JsonProperty("payment_currency")
    private PaymentCurrency paymentCurrency; // 결제 통화 (마켓)
}
