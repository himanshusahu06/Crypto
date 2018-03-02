package com.crypto.portfolio.cryptoportfolio.apiclient;


import com.crypto.portfolio.cryptoportfolio.builder.urlBuilder.BinanceURLBuilder;
import com.crypto.portfolio.cryptoportfolio.dto.response.binance.BinanceMarket;
import com.crypto.portfolio.cryptoportfolio.dto.response.binance.GetAccountBalanceResponse;
import com.crypto.portfolio.cryptoportfolio.dto.response.binance.OpenOrderDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinanceClient {

    private <T> T getDataFromBinance(String url, Map<String, String> headers, Class<T> type) {

        HTTPClient httpClient = new HTTPClient();

        T tObject = httpClient.getResponseBody(url, headers, type);

        return tObject;
    }

    public List<OpenOrderDTO> getOpenOrders(String apiKey, String apiSecret) {

        String binanceURL = BinanceURLBuilder.getOpenOrderURL(apiSecret);

        Map<String, String> headers = getHeaders(apiKey);

        List<OpenOrderDTO> openOrderDTOS =  getDataFromBinance(binanceURL, headers, List.class);

        return openOrderDTOS;
    }

    public List<BinanceMarket> getMarket() {

        List<BinanceMarket> market = new ArrayList<>();

        String binanceURL = BinanceURLBuilder.getMarketURL();

        BinanceMarket[] binanceMarketArray =  getDataFromBinance(binanceURL, null, BinanceMarket[].class);

        Collections.addAll(market, binanceMarketArray);

        return market;
    }

    public GetAccountBalanceResponse getAccountBalance(String apiKey, String apiSecret) {

        String binanceURL = BinanceURLBuilder.getAccountBalanceURL(apiSecret);

        Map<String, String> headers = getHeaders(apiKey);

        GetAccountBalanceResponse accountBalanceResponse =  getDataFromBinance(binanceURL, headers, GetAccountBalanceResponse.class);

        return accountBalanceResponse;
    }

    private Map<String, String> getHeaders(String apiKey) {

        Map<String, String> headers = new HashMap<>();

        headers.put("X-MBX-APIKEY", apiKey);

        return headers;
    }

    public static void main(String[] args) {
        BinanceClient binanceClient = new BinanceClient();
        binanceClient.getAccountBalance("5NJxYgIG2mAkRx5JfHC1s6Nh9y1vii3mvPJ51jqi2xIYugTddQL1dPMJ4pPRTxmN", "nbuzlGgG6syuMScoSf7RMmF1yyn0ULGumGYkO7POERgKyWmY4IAUVKTzyKupHGBX");
    }

}
