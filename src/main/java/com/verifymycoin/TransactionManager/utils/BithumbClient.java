package com.verifymycoin.TransactionManager.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BithumbClient {

    private final Environment env;

    public static String encodeURIComponent(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8)
            .replaceAll("\\+", "%20")
            .replaceAll("\\%21", "!")
            .replaceAll("\\%27", "'")
            .replaceAll("\\%28", "(")
            .replaceAll("\\%29", ")")
            .replaceAll("\\%26", "&")
            .replaceAll("\\%3D", "=")
            .replaceAll("\\%7E", "~");
    }

    public static byte[] hmacSha512(String value, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");

            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(keySpec);

            final byte[] macData = mac.doFinal(value.getBytes());
            return new Hex().encode(macData);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static String asHex(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    private String usecTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String request(String strHost, String strMemod, HashMap<String, String> rgParams,
        HashMap<String, String> httpHeaders) {
        String response = "";

        if (strHost.startsWith("https://")) {
            HttpRequest request = HttpRequest.get(strHost);
            // Accept all certificates
            request.trustAllCerts();
            // Accept all hostnames
            request.trustAllHosts();
        }

        HttpRequest request = null;

        if (strMemod.equalsIgnoreCase("POST")) {

            request = new HttpRequest(strHost, "POST");
            request.readTimeout(10000);

            System.out.println("POST ==> " + request.url());

            if (httpHeaders != null && !httpHeaders.isEmpty()) {
                httpHeaders.put("api-client-type", "2");
                request.headers(httpHeaders);
                System.out.println(httpHeaders);
            }
            if (rgParams != null && !rgParams.isEmpty()) {
                request.form(rgParams);
                System.out.println(rgParams);
            }
        } else {
            request = HttpRequest.get(strHost + BithumbUtils.mapToQueryString(rgParams));
            request.readTimeout(10000);

            System.out.println("Response was: " + response);
        }

        if (request.ok()) {
            response = request.body();
        } else {
            response = "error : " + request.code() + ", message : " + request.body();
        }
        request.disconnect();
        return response;
    }

    private HashMap<String, String> getHttpHeaders(String endpoint, HashMap<String, String> rgData, String apiKey,
        String apiSecret) {

        String strData = BithumbUtils.mapToQueryString(rgData).replace("?", "");
        String nNonce = usecTime();

        strData = strData.substring(0, strData.length() - 1);

        System.out.println("1 : " + strData);

        strData = encodeURIComponent(strData);

        HashMap<String, String> array = new HashMap<>();

        String str = endpoint + ";" + strData + ";" + nNonce;
        String encoded = asHex(hmacSha512(str, apiSecret));

        System.out.println("strData was: " + str);
        System.out.println("apiSecret was: " + apiSecret);
        array.put("Api-Key", apiKey);
        array.put("Api-Sign", encoded);
        array.put("Api-Nonce", nNonce);

        return array;

    }

    public String callApi(String endpoint, HashMap<String, String> params, String apiKey, String apiSecret) {
        HashMap<String, String> rgParams = new HashMap<>();
        rgParams.put("endpoint", endpoint);

        if (params != null) {
            rgParams.putAll(params);
        }

        String api_host = env.getProperty("api.default") + endpoint;
        HashMap<String, String> httpHeaders = getHttpHeaders(endpoint, rgParams, apiKey, apiSecret);

        String rgResultDecode = request(api_host, "POST", rgParams, httpHeaders);

        if (!rgResultDecode.startsWith("error")) {
            HashMap<String, String> result;
            try {
                result = new ObjectMapper().readValue(rgResultDecode, HashMap.class);
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rgResultDecode;
    }
}
