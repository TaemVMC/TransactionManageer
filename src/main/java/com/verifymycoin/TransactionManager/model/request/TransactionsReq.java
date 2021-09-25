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
    private Date fromDate;                      // 조회 시작일

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date toDate;                        // 조회 종료일
}
