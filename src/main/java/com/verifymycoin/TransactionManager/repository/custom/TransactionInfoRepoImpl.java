package com.verifymycoin.TransactionManager.repository.custom;

import static com.verifymycoin.TransactionManager.model.entity.QTransactionInfo.transactionInfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.verifymycoin.TransactionManager.model.dto.QTransactionSummaryDto;
import com.verifymycoin.TransactionManager.model.dto.TransactionSummaryDto;
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

    /**
     * 사용자 수익률 계산
     *
     * @param req
     * @param exchangeId
     * @param userId
     * @return
     */
    @Override
    public List<TransactionSummaryDto> calcTransactionSummary(TransactionsReq req, Integer exchangeId,
        String userId) {
        return queryFactory
            .from(transactionInfo)
            .where(transactionInfo.userId.eq(userId)
                .and(transactionInfo.exchangeId.eq(exchangeId))
                .and(transactionInfo.orderCoinSymbol.eq(req.getOrderCurrency()))
                .and(transactionInfo.paymentCurrencySymbol.eq(req.getPaymentCurrency().getCode())))
            .groupBy(transactionInfo.type)
            .select(
                new QTransactionSummaryDto(
                    transactionInfo.type,
                    transactionInfo.units.sum().as("total_units"),
                    transactionInfo.amount.sum().as("total_amount"),
                    transactionInfo.price.multiply(transactionInfo.units).sum(),
                    transactionInfo.amount.sum(),
                    transactionInfo.transferDate.max(),
                    transactionInfo.count())
            ).fetch();
    }
}
