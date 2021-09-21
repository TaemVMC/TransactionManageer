package com.verifymycoin.TransactionManager.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoinExchangeRes {

    private int id;

    private String coinSymbol;

    private String coinName;

    @Builder
    public CoinExchangeRes(int id, String coinSymbol, String coinName) {
        this.id = id;
        this.coinSymbol = coinSymbol;
        this.coinName = coinName;
    }
}
