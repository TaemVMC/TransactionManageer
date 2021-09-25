package com.verifymycoin.TransactionManager.model.request;

import com.verifymycoin.TransactionManager.common.enums.PaymentCurrency;
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
}
