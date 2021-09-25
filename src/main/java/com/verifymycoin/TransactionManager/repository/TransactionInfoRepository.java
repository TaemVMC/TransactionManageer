package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.TransactionInfo;
import org.springframework.data.repository.CrudRepository;

public interface TransactionInfoRepository extends CrudRepository<TransactionInfo, Long> {

}
