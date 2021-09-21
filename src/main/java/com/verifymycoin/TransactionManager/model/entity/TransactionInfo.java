package com.verifymycoin.TransactionManager.model.entity;

import com.verifymycoin.TransactionManager.common.enums.TransactionInfoType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private TransactionInfoType type;

    @ManyToOne(targetEntity = Exchange.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_id", nullable = false)
    private Exchange exchange;

    @ManyToOne(targetEntity = Coin.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_coin_symbol", nullable = false)
    private Coin coin;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(targetEntity = PaymentCurrency.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_currency", nullable = false)
    private PaymentCurrency paymentCurrency;

    @Column(nullable = false)
    private Float units;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Float fee;

    @Column(nullable = false)
    private LocalDateTime transferDate;
}
