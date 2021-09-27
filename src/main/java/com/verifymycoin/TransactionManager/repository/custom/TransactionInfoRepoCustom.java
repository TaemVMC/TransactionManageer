package com.verifymycoin.TransactionManager.repository.custom;

import com.verifymycoin.TransactionManager.model.entity.TransactionInfo;
import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
import java.util.List;

public interface TransactionInfoRepoCustom {

    boolean existsTransactionInfo(final TransactionsReq req, final Integer exchangeId, final String userId);

    List<TransactionInfo> findAllTransactionInfo(final TransactionsReq req, final Integer exchangeId,
        final String userId);
}
