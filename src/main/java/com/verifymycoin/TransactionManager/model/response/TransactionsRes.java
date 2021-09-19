package com.verifymycoin.TransactionManager.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TransactionsRes {

    private String status;              // 결과 상태 코드
    private String search;              // 검색 구분

    @JsonProperty("transfer_date")
    private Integer transferDate;       // 거래 일시 Timestamp

    @JsonProperty("order_currency")
    private String orderCurrency;       // 주문 통화 (코인)

    @JsonProperty("payment_currency")
    private String paymentCurrency;     // 결제 통화 (마켓)

    private String units;               // 거래요청 currency 수량
    private String price;               // 1 currency 당 가격
    private String amount;              // 거래 금액

    @JsonProperty("fee_currency")
    private String feeCurrency;         // 수수료 통화

    private String fee;                 // 거래 수수료

    @JsonProperty("order_balance")
    private String orderBalance;        // 주문 통화 잔액

    @JsonProperty("payment_balance")
    private String paymentBalance;      // 결제 통화 잔액
}
