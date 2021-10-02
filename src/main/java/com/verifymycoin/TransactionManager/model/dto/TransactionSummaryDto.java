package com.verifymycoin.TransactionManager.model.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionSummaryDto {

    private String type;
    private double totalUnits;
    private double totalAmount;
    private double averageCost;
    private long lastDate;
    private long count;

    @QueryProjection
    public TransactionSummaryDto(String type, double totalUnits, double totalAmount, double averageCost1,
        double averageCost2, long lastDate, long count) {
        this.type = type;
        this.totalUnits = totalUnits;
        this.totalAmount = totalAmount;
        this.averageCost = averageCost1 / averageCost2;
        this.lastDate = lastDate;
        this.count = count;
    }
}
