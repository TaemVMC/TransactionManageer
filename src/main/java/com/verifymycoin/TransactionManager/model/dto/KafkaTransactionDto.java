package com.verifymycoin.TransactionManager.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTransactionDto {

    private String exchangeName;
    private String userId;
    private String orderCurrency;
    private String paymentCurrency;
    private Float units;
    private Float profit;
    private Float averageCost;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
