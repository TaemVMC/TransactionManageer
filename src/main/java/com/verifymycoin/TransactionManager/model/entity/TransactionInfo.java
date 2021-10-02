package com.verifymycoin.TransactionManager.model.entity;

import com.verifymycoin.TransactionManager.model.dto.TransactionsDataDto;
import com.verifymycoin.TransactionManager.utils.Utils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "transaction_info")
public class TransactionInfo {

    @Id
    @Column(nullable = false, length = 32)
    private String id;

    @Column(nullable = false)
    private String type;

    @Column(name = "exchange_id", nullable = false)
    private Integer exchangeId;

    @Column(name = "order_coin_symbol", nullable = false, length = 10)
    private String orderCoinSymbol;

    @Column(name = "payment_currency_symbol", nullable = false, length = 10)
    private String paymentCurrencySymbol;

    @Column(nullable = false, length = 25)
    private String userId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double units;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double fee;

    @Column(nullable = false)
    private Double orderBalance;

    @Column(nullable = false)
    private Long transferDate;

    @ManyToOne(targetEntity = Exchange.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id", nullable = false, insertable = false, updatable = false)
    private Exchange exchange;

    @ManyToOne(targetEntity = PaymentCurrency.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_currency_symbol", nullable = false, insertable = false, updatable = false)
    private PaymentCurrency paymentCurrency;

    @ManyToOne(targetEntity = Coin.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_coin_symbol", nullable = false, insertable = false, updatable = false)
    private Coin coin;

    public TransactionInfo(TransactionsDataDto dto, Integer exchangeId, String userId) {
        String source = dto.getSearch().name()
            + exchangeId + dto.getOrderCurrency() + dto.getPaymentCurrency()
            + dto.getTransferDate();

        this.id = Utils.generateTransactionId(source);
        this.type = dto.getSearch().name();
        this.exchangeId = exchangeId;
        this.orderCoinSymbol = dto.getOrderCurrency();
        this.paymentCurrencySymbol = dto.getPaymentCurrency();
        this.userId = userId;
        this.price = dto.getPrice();
        this.units = dto.getUnits();
        this.amount = dto.getAmount();
        this.fee = dto.getFee();
        this.orderBalance = dto.getOrderBalance();
        this.transferDate = dto.getTransferDate();
    }
}
