package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepo extends JpaRepository<Exchange, Integer> {

}
