package com.verifymycoin.TransactionManager.model.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeRes {

    private Integer id;

    private String exchangeName;
}
