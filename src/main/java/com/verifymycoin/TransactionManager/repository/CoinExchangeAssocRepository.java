package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.CoinExchangeAssoc;
import org.springframework.data.repository.CrudRepository;

public interface CoinExchangeAssocRepository extends CrudRepository<CoinExchangeAssoc, Integer> {

    Iterable<CoinExchangeAssoc> findByExchangeId(Integer exchangeId);
}
