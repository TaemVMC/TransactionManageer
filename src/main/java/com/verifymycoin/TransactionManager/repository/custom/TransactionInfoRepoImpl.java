package com.verifymycoin.TransactionManager.repository.custom;

import static com.verifymycoin.TransactionManager.model.entity.QTransactionInfo.transactionInfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.verifymycoin.TransactionManager.model.entity.TransactionInfo;
import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
import java.util.List;
import javax.persistence.EntityManager;

public class TransactionInfoRepoImpl implements TransactionInfoRepoCustom {

    private final JPAQueryFactory queryFactory;

    public TransactionInfoRepoImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 거래정보가 존재하는지 check
     *
     * @param req
     * @param exchangeId 거래소 ID
     * @param userId     유저 ID
     * @return
     */
    @Override
    public boolean existsTransactionInfo(TransactionsReq req, Integer exchangeId, String userId) {
        Long endDateMillSeconds = req.getEndDate().getTime();

        Integer fetchOne = queryFactory
            .selectOne()
            .from(transactionInfo)
            .where(transactionInfo.exchangeId.eq(exchangeId)
                .and(transactionInfo.userId.eq(userId))
                .and(transactionInfo.orderCoinSymbol.eq(req.getOrderCurrency()))
                .and(transactionInfo.paymentCurrencySymbol.eq(req.getPaymentCurrency().getCode()))
                .and(transactionInfo.transferDate.goe(endDateMillSeconds)))
            .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public List<TransactionInfo> findAllTransactionInfo(TransactionsReq req, Integer exchangeId, String userId) {
        Long startDate = req.getStartDate().getTime();
        Long endDate = req.getEndDate().getTime();

        return queryFactory
            .selectFrom(transactionInfo)
            .where(transactionInfo.exchangeId.eq(exchangeId)
                .and(transactionInfo.userId.eq(userId))
                .and(transactionInfo.orderCoinSymbol.eq(req.getOrderCurrency()))
                .and(transactionInfo.paymentCurrencySymbol.eq(req.getPaymentCurrency().getCode()))
                .and(transactionInfo.transferDate.between(startDate, endDate)))
            .orderBy(transactionInfo.transferDate.asc()).fetch();
    }
}
