package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.PaymentCurrencyExchangeAssoc;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PaymentCurrencyExchangeAssocRepository extends CrudRepository<PaymentCurrencyExchangeAssoc, Integer> {

    List<PaymentCurrencyExchangeAssoc> findByExchangeId(Integer exchangeId);
}
