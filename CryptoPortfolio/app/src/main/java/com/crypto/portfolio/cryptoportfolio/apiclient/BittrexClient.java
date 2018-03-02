package com.crypto.portfolio.cryptoportfolio.apiclient;

import com.crypto.portfolio.cryptoportfolio.builder.urlBuilder.BittrexURLBuilder;
import com.crypto.portfolio.cryptoportfolio.dto.ApiError;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.account.AccountBalanceResponse;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.market.MarketSummaryDTO;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.market.MarketSummaryResponse;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.openorder.OpenOrderResponse;

import java.util.HashMap;
import java.util.Map;

public class BittrexClient {

    public AccountBalanceResponse getAccountBalance(String apiKey, String apiSecret) {

        String url = BittrexURLBuilder.getAccountBalanceURL(apiKey);

        Map<String, String> headers = getHeaders(apiSecret, url);

        AccountBalanceResponse bittrexResponse = getDataFromBittrex(url, headers, AccountBalanceResponse.class);

        if (bittrexResponse == null) {
            bittrexResponse = new AccountBalanceResponse();
            String diagnosticMessage = "NO_INTERNET_CONNECTION";
            bittrexResponse.setMessage(diagnosticMessage);
            bittrexResponse.setSuccess(Boolean.FALSE);
            ApiError apiError = new ApiError("No internet connection", diagnosticMessage);
            bittrexResponse.setApiError(apiError);
        } else {
            if (bittrexResponse.getMessage().equals("INVALID_SIGNATURE") || bittrexResponse.getMessage().equals("APIKEY_INVALID")) {
                ApiError apiError = new ApiError("Bittrex API key or secret is invalid", bittrexResponse.getMessage());
                bittrexResponse.setApiError(apiError);
            }
        }

        return bittrexResponse;
    }

    public Map<String, MarketSummaryDTO> getMarketSummary() {

        Map<String, MarketSummaryDTO> marketSummaryResponse = new HashMap<>();

        String url = BittrexURLBuilder.getMarketSummaryURL();

        MarketSummaryResponse summaryResponse = getDataFromBittrex(url, null, MarketSummaryResponse.class);

        for (MarketSummaryDTO marketSummaryDTO : summaryResponse.getResult()) {
            marketSummaryResponse.put(marketSummaryDTO.getMarketName(), marketSummaryDTO);
        }

        return marketSummaryResponse;
    }

    public OpenOrderResponse getOpenOrders(String apiKey, String apiSecret) {

        String url = BittrexURLBuilder.getOpenOrderURL(apiKey);

        Map<String, String> headers = getHeaders(apiSecret, url);

        OpenOrderResponse openOrderResponse = getDataFromBittrex(url, headers, OpenOrderResponse.class);

        if (openOrderResponse == null) {
            openOrderResponse = new OpenOrderResponse();
            String diagnosticMessage = "NO_INTERNET_CONNECTION";
            openOrderResponse.setMessage(diagnosticMessage);
            openOrderResponse.setSuccess(Boolean.FALSE);
            ApiError apiError = new ApiError("No internet connection", diagnosticMessage);
            openOrderResponse.setApiError(apiError);
        } else {
            if (openOrderResponse.getMessage().equals("INVALID_SIGNATURE") || openOrderResponse.getMessage().equals("APIKEY_INVALID")) {
                ApiError apiError = new ApiError("Bittrex API key or secret is invalid", openOrderResponse.getMessage());
                openOrderResponse.setApiError(apiError);
            }
        }

        return openOrderResponse;
    }

    private <T> T getDataFromBittrex(String url, Map<String, String> headers, Class<T> type) {

        HTTPClient httpClient = new HTTPClient();

        T tObject = httpClient.getResponseBody(url, headers, type);

        return tObject;
    }

    private Map<String, String> getHeaders(String apiSecret, String url) {

        Map<String, String> headers = new HashMap<>();

        String sign = APISign.sign(apiSecret, url, APISign.HMACSHA212);

        headers.put("apiSign", sign);

        return headers;
    }
}
