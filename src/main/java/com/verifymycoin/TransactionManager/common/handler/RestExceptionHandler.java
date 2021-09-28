package com.verifymycoin.TransactionManager.common.handler;

import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.NOT_FOUND_EXCHANGE_ID;
import static com.verifymycoin.TransactionManager.common.enums.ErrorCode.NOT_FOUND_TRANSACTION;

import com.verifymycoin.TransactionManager.common.exceptions.CustomRequestException;
import com.verifymycoin.TransactionManager.common.exceptions.NotFoundExchangeIdException;
import com.verifymycoin.TransactionManager.common.exceptions.NotFoundTransactionException;
import com.verifymycoin.TransactionManager.common.exceptions.TransactionsApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    // TODO RuntimeException 상속 Error handling 리펙토링
    @ExceptionHandler(value = TransactionsApiException.class)
    public ResponseEntity<?> handleTransactionsApiException(final TransactionsApiException e) {
        return ResponseEntity.badRequest().body(new CustomRequestException(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = NotFoundExchangeIdException.class)
    public ResponseEntity<?> handleNotFoundExchangeIdException(final NotFoundExchangeIdException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomRequestException(NOT_FOUND_EXCHANGE_ID));
    }

    @ExceptionHandler(value = NotFoundTransactionException.class)
    public ResponseEntity<?> handleNotFoundTransactionException(final NotFoundTransactionException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomRequestException(NOT_FOUND_TRANSACTION));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(final Exception e) {
        log.error("Internal Server error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CustomRequestException(INTERNAL_SERVER_ERROR));
    }
}
