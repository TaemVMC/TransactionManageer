package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.PaymentCurrencyExchangeAssoc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCurrencyExchangeAssocRepo extends JpaRepository<PaymentCurrencyExchangeAssoc, Integer> {

    List<PaymentCurrencyExchangeAssoc> findByExchangeId(Integer exchangeId);
}
