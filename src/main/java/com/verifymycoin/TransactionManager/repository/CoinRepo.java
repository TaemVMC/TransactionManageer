package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepo extends JpaRepository<Coin, String> {

}
