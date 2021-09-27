package com.verifymycoin.TransactionManager.repository;

import com.verifymycoin.TransactionManager.model.entity.TransactionInfo;
import com.verifymycoin.TransactionManager.repository.custom.TransactionInfoRepoCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionInfoRepo extends JpaRepository<TransactionInfo, Long>, TransactionInfoRepoCustom {

}
