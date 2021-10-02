package com.verifymycoin.TransactionManager.repository.custom;

import com.verifymycoin.TransactionManager.model.dto.TransactionSummaryDto;
import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
import java.util.List;

public interface TransactionInfoRepoCustom {

    boolean existsTransactionInfo(final TransactionsReq req, final Integer exchangeId, final String userId);

    List<TransactionSummaryDto> calcTransactionSummary(final TransactionsReq req, final Integer exchangeId,
        final String userId);
}
