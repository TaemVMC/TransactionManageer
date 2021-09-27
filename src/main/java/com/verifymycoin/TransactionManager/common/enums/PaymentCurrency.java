package com.verifymycoin.TransactionManager.common.enums;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentCurrency {

    KRW("KRW", "원화"), BTC("BTC", "비트코인");

    private static final Map<String, PaymentCurrency> map = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(PaymentCurrency::getCode, Function.identity())));
    
    private String code;
    private String description;

    public static PaymentCurrency find(String paymentCurrency) {
        return Optional.ofNullable(map.get(paymentCurrency)).orElse(KRW);
    }
}
