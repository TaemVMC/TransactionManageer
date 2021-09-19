package com.verifymycoin.TransactionManager.utils;

import java.util.Map;
import java.util.Map.Entry;

public class BithumbUtils {

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
}
