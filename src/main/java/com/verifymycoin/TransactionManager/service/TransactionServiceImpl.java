package com.verifymycoin.TransactionManager.service;

import com.verifymycoin.TransactionManager.utils.BithumbClient;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final BithumbClient bithumbClient;
    private final Environment env;

    public void getTransactions() {
        HashMap<String, String> rgParams = new HashMap<>();
        rgParams.put("searchGb", "0");
        rgParams.put("order_currency", "BTC");
        rgParams.put("payment_currency", "KRW");

        try {
            String API_KEY = env.getProperty("key.api-key");
            String API_SECRET = env.getProperty("key.secret-key");

            String result = bithumbClient.callApi("/info/user_transactions", rgParams, API_KEY, API_SECRET);
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
