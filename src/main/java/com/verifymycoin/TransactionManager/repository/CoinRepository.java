package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, String> {

}
