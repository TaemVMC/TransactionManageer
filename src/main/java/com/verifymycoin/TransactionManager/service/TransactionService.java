package com.verifymycoin.TransactionManager.service;

import com.verifymycoin.TransactionManager.model.dto.TransactionsDataDto;
import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
import com.verifymycoin.TransactionManager.model.response.CoinExchangeRes;
import com.verifymycoin.TransactionManager.model.response.ExchangeRes;
import com.verifymycoin.TransactionManager.model.response.PaymentCurrencyRes;
import java.util.List;

public interface TransactionService {

    List<ExchangeRes> getExchanges();

    List<PaymentCurrencyRes> getPaymentCurrencyByExchangeId(final Integer exchangeId);

    List<CoinExchangeRes> getCoinListByExchangeId(final Integer exchangeId);

    List<TransactionsDataDto> getTransactions(final TransactionsReq req, final Integer exchangeId, final String userId)
        throws Exception;

    void saveTransactionInfos(final List<TransactionsDataDto> data, final Integer exchangeId, final String userId);
}
