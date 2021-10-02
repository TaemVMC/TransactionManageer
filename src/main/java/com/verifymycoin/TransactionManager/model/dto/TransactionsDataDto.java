package com.verifymycoin.TransactionManager.model.dto;

import com.verifymycoin.TransactionManager.common.enums.SearchGb;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionsDataDto {

    private SearchGb search;

    private Long transferDate;

    private String orderCurrency;
    private String paymentCurrency;
    private Double units;
    private Double price;
    private Double amount;
    private Double fee;
    private Double orderBalance;

    public void setSearch(String search) {
        this.search = SearchGb.find(search);
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = TimeUnit.MICROSECONDS.toMillis(Long.parseLong(transferDate));
    }

    public void setUnits(String units) {
        units = units.replaceAll(" ", "");
        this.units = Double.parseDouble(units);
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public void setAmount(String amount) {
        this.amount = Double.parseDouble(amount);
    }

    public void setFee(String fee) {
        this.fee = Double.parseDouble(fee);
    }

    public void setOrderBalance(String orderBalance) {
        this.orderBalance = Double.parseDouble(orderBalance);
    }
}
