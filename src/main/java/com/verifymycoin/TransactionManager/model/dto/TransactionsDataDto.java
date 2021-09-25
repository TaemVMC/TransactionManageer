package com.verifymycoin.TransactionManager.model.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionsDataDto {

    private String search;
    private LocalDateTime transferDate;
    private String orderCurrency;
    private String paymentCurrency;
    private Float units;
    private Float price;
    private Integer amount;
    private Float fee;

    public void setTransferDate(String transferDate) {
        long milliseconds = TimeUnit.MICROSECONDS.toMillis(Long.parseLong(transferDate));
        Instant instant = Instant.ofEpochMilli(milliseconds);
        this.transferDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setUnits(String units) {
        this.units = Float.parseFloat(units);
    }

    public void setPrice(String price) {
        this.price = Float.parseFloat(price);
    }

    public void setAmount(String amount) {
        this.amount = Integer.parseInt(amount);
    }

    public void setFee(String fee) {
        this.fee = Float.parseFloat(fee);
    }
}
