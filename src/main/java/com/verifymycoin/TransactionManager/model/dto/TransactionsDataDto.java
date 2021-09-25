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
    private Float units;
    private Float price;
    private Float amount;
    private Float fee;

    public void setSearch(String search) {
        this.search = SearchGb.find(search);
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = TimeUnit.MICROSECONDS.toMillis(Long.parseLong(transferDate));
    }

    public void setUnits(String units) {
        this.units = Float.parseFloat(units);
    }

    public void setPrice(String price) {
        this.price = Float.parseFloat(price);
    }

    public void setAmount(String amount) {
        this.amount = Float.parseFloat(amount);
    }

    public void setFee(String fee) {
        this.fee = Float.parseFloat(fee);
    }
}
