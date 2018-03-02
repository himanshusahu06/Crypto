package com.crypto.portfolio.cryptoportfolio.dto.response.binance;

import org.json.JSONException;
import org.json.JSONObject;

public class BinanceMarket {

    private String symbol;
    private Double price;

    public BinanceMarket() {
        super();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BinanceMarket{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
