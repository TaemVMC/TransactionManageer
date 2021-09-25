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
public enum SearchGb {

    ALL("0", "전체"), BUY("1", "매수 완료"), SELL("2", "매도 완료"), WITHDRAW_CONTINUE("3", "출금 중"),
    DEPOSIT_COMPLETED("4", "입금"), WITHDRAW_COMPLETED("5", "출금"), DEPOSIT_CONTINUE("9", "KRW 입금 중");

    private static final Map<String, SearchGb> map = Collections.unmodifiableMap(
        Stream.of(values()).collect(Collectors.toMap(SearchGb::getCode, Function.identity())));

    private String code;
    private String description;

    public static SearchGb find(String search) {
        return Optional.ofNullable(map.get(search)).orElse(ALL);
    }
}
