package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.CoinExchangeAssoc;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CoinExchangeAssocRepository extends CrudRepository<CoinExchangeAssoc, Integer> {

    List<CoinExchangeAssoc> findByExchangeId(Integer exchangeId);
}
