package com.crypto.portfolio.cryptoportfolio.builder.urlBuilder;

import com.crypto.portfolio.cryptoportfolio.apiclient.APISign;

public class BittrexURLBuilder {

    private final static String INITIAL_URL = "https://bittrex.com/api/v1.1";
    private final static String ACCOUNT = "/account";
    private final static String MARKET = "/market";
    private final static String PUBLIC = "/public";
    private final static String GET_BALANCE = "/getbalances";
    private final static String GET_OPEN_ORDER = "/getopenorders";
    private final static String GET_MARKET_SUMMARY = "/getmarketsummaries";
    private final static String API_KEY_QUERY_PARAM = "apikey";
    private final static String NONCE_QUERY_PARAM = "nonce";
    private final static String QUERY = "?";
    private final static String AND = "&";
    private final static String EQUAL = "=";

    public static String getAccountBalanceURL(String apiKey) {

        String nonce = APISign.generateNonce();

        StringBuilder stringBuilder = new StringBuilder(INITIAL_URL);
        stringBuilder
                .append(ACCOUNT)
                .append(GET_BALANCE)
                .append(QUERY)
                .append(API_KEY_QUERY_PARAM).append(EQUAL).append(apiKey)
                .append(AND)
                .append(NONCE_QUERY_PARAM).append(EQUAL).append(nonce);

        return stringBuilder.toString();
    }

    public static String getOpenOrderURL(String apiKey) {

        String nonce = APISign.generateNonce();

        StringBuilder stringBuilder = new StringBuilder(INITIAL_URL);
        stringBuilder
                .append(MARKET)
                .append(GET_OPEN_ORDER)
                .append(QUERY)
                .append(API_KEY_QUERY_PARAM).append(EQUAL).append(apiKey)
                .append(AND)
                .append(NONCE_QUERY_PARAM).append(EQUAL).append(nonce);

        return stringBuilder.toString();
    }

    public static String getMarketSummaryURL() {
        StringBuilder stringBuilder = new StringBuilder(INITIAL_URL);
        stringBuilder.append(PUBLIC).append(GET_MARKET_SUMMARY);
        return stringBuilder.toString();
    }
}