package com.crypto.portfolio.cryptoportfolio.builder.urlBuilder;

import com.crypto.portfolio.cryptoportfolio.apiclient.APISign;

public class BinanceURLBuilder {

    private final static String INITIAL_URL = "https://api.binance.com/api";
    private final static String VERSION_V1 = "/v1";
    private final static String VERSION_V2 = "/v2";
    private final static String VERSION_V3 = "/v3";
    private final static String ACCOUNT = "/account";
    private final static String OPEN_ORDER = "/allOrders";
    private final static String MARKET = "/ticker/allPrices";
    private final static String QUERY = "?";
    private final static String AND = "&";
    private final static String EQUAL = "=";
    private final static String TIMESTAMP = "timestamp";
    private final static String SUGNATURE = "signature";


    private static String appendSignatureToURL(String binanceURL, String signature) {

        return binanceURL + AND + SUGNATURE + EQUAL + signature;
    }

    public static String getMarketURL() {

        return INITIAL_URL + VERSION_V1 + MARKET;
    }

    public static String getAccountBalanceURL(String apiSecret) {

        String timestamp = APISign.generateNonce();

        String queryParam = TIMESTAMP + EQUAL + timestamp;

        String binanceURL = INITIAL_URL + VERSION_V3 + ACCOUNT + QUERY + queryParam;

        String signature = APISign.sign(apiSecret, queryParam, APISign.HMACSHA256);

        binanceURL = appendSignatureToURL(binanceURL, signature);

        return  binanceURL;
    }

    public static String getOpenOrderURL(String apiSecret) {

        String timestamp = APISign.generateNonce();

        String queryParam = TIMESTAMP + EQUAL + timestamp;

        String binanceURL = INITIAL_URL + VERSION_V3 + OPEN_ORDER + QUERY + queryParam;

        String signature = APISign.sign(apiSecret, queryParam, APISign.HMACSHA256);

        binanceURL = appendSignatureToURL(binanceURL, signature);

        return  binanceURL;
    }
}
