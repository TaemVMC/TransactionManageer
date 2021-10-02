package com.verifymycoin.TransactionManager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTransactionDto {

    private String exchangeName;
    private String userId;
    private String orderCurrency;
    private String paymentCurrency;

    private double profit;
    private double yield;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Builder
    public KafkaTransactionDto(String exchangeName, String userId, String orderCurrency, String paymentCurrency,
        double buyAmount, double sellAmount, Date endDate) {
        this.exchangeName = exchangeName;
        this.userId = userId;
        this.orderCurrency = orderCurrency;
        this.paymentCurrency = paymentCurrency;

        this.profit = sellAmount - buyAmount;
        this.yield = this.profit / buyAmount;
        this.endDate = endDate;
    }
}