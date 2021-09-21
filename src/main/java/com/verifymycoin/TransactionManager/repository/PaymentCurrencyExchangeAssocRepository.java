package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.PaymentCurrencyExchangeAssoc;
import org.springframework.data.repository.CrudRepository;

public interface PaymentCurrencyExchangeAssocRepository extends CrudRepository<PaymentCurrencyExchangeAssoc, Integer> {

    Iterable<PaymentCurrencyExchangeAssoc> findByExchangeId(Integer exchangeId);

}
