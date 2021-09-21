package com.verifymycoin.TransactionManager.service;

import com.verifymycoin.TransactionManager.model.response.CoinExchangeRes;
import com.verifymycoin.TransactionManager.model.response.ExchangeRes;
import com.verifymycoin.TransactionManager.model.response.PaymentCurrencyRes;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    List<ExchangeRes> getExchanges();

    List<PaymentCurrencyRes> getPaymentCurrencyByExchangeId(Integer exchangeId);

    List<CoinExchangeRes> getCoinListByExchangeId(Integer exchangeId);

    Map<String, String> getTransactions() throws Exception;
}
