package com.verifymycoin.TransactionManager.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;

public class Utils {

    public static String mapToQueryString(Map<String, String> map) {
        StringBuilder string = new StringBuilder();

        if (map.size() > 0) {
            string.append("?");
        }

        for (Entry<String, String> entry : map.entrySet()) {
            string.append(entry.getKey());
            string.append("=");
            string.append(entry.getValue());
            string.append("&");
        }
        return string.toString();
    }

    public static String generateTransactionId(String source) {
        return Hashing.sha256().hashString(source, StandardCharsets.UTF_8).toString();
    }
}
