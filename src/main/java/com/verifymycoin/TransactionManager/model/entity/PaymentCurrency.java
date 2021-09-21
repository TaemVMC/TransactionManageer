package com.verifymycoin.TransactionManager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_currency")
public class PaymentCurrency {

    @Id
    private String symbol;

    @Column(nullable = false)
    private String name;
}
