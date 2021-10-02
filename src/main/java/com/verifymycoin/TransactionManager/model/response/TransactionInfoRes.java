package com.verifymycoin.TransactionManager.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionInfoRes {

    private long totalCount;

    private long sellCount;
    private long buyCount;
}
