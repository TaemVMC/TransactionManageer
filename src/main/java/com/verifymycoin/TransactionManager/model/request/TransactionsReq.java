package com.verifymycoin.TransactionManager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.verifymycoin.TransactionManager.common.enums.PaymentCurrency;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionsReq {

    private String apiKey;                      // 사용자 API
    private String secretKey;                   // 사용자 Secret key
    private String orderCurrency;               // 주문 통화 (코인)
    private PaymentCurrency paymentCurrency;    // 결제 통화 (마켓)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate = new Date();          // 조회 종료일

    public TransactionsReq(String apiKey, String secretKey, String orderCurrency, String paymentCurrency,
        Date endDate) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.orderCurrency = orderCurrency;
        this.paymentCurrency = PaymentCurrency.find(paymentCurrency);
        this.endDate = endDate != null ? endDate : new Date();
    }

    public TransactionsReq(String apiKey, String secretKey, String orderCurrency, String paymentCurrency) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.orderCurrency = orderCurrency;
        this.paymentCurrency = PaymentCurrency.find(paymentCurrency);
    }
}
