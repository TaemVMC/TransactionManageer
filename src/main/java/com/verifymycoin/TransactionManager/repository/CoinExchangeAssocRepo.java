package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.CoinExchangeAssoc;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinExchangeAssocRepo extends JpaRepository<CoinExchangeAssoc, Integer> {

    List<CoinExchangeAssoc> findByExchangeId(Integer exchangeId);
}
