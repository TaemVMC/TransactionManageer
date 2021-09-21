package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.Exchange;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRepository extends CrudRepository<Exchange, Integer> {
    
}
