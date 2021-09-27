package com.verifymycoin.TransactionManager.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

import com.verifymycoin.TransactionManager.model.entity.TransactionInfo;
import com.verifymycoin.TransactionManager.model.request.TransactionsReq;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class TransactionInfoRepoTest {

    private static final String API_KEY = "2138796b020ece9597584f793e580551";
    private static final String SECRET_KEY = "dd0330a1d7faee9f27f24184b6927edf";
    
    @Autowired
    private TransactionInfoRepo transactionInfoRepo;

    @Test
    public void existsTransactionInfo() throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-01");

        TransactionsReq req = new TransactionsReq(API_KEY, SECRET_KEY, "DOT", "KRW", startDate, endDate);
        boolean check = transactionInfoRepo.existsTransactionInfo(req, 1, "test123");
        assertThat(check, is(true));
    }

    @Test
    public void findAllTransactionInfo() throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-01");

        TransactionsReq req = new TransactionsReq(API_KEY, SECRET_KEY, "DOT", "KRW", startDate, endDate);

        List<TransactionInfo> infos = transactionInfoRepo.findAllTransactionInfo(req, 1, "test123");
        assertThat(infos.size(), greaterThanOrEqualTo(1));
    }
}