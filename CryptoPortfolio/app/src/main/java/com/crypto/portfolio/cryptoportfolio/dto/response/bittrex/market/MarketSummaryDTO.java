package com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.market;


import java.io.Serializable;

/**
 * MarketName": "BTC-1ST",
 "High": 0.00012111,
 "Low": 0.000109,
 "Volume": 1127688.11516663,
 "Last": 0.0001173,
 "BaseVolume": 129.80428683,
 "TimeStamp": "2018-01-15T16:51:11.55",
 "Bid": 0.000116,
 "Ask": 0.0001173,
 "OpenBuyOrders": 680,
 "OpenSellOrders": 5405,
 "PrevDay": 0.00011359,
 "Created": "2017-06-06T01:22:35.727"
 */

public class MarketSummaryDTO {
    private String MarketName;
    private Double High;
    private Double Low;
    private Double Last;
    private Double PrevDay;

    public MarketSummaryDTO() {
        super();
    }

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public Double getHigh() {
        return High;
    }

    public void setHigh(Double high) {
        High = high;
    }

    public Double getLow() {
        return Low;
    }

    public void setLow(Double low) {
        Low = low;
    }

    public Double getLast() {
        return Last;
    }

    public void setLast(Double last) {
        Last = last;
    }

    public Double getPrevDay() {
        return PrevDay;
    }

    public void setPrevDay(Double prevDay) {
        PrevDay = prevDay;
    }

    @Override
    public String toString() {
        return "MarketSummaryDTO{" +
                "MarketName='" + MarketName + '\'' +
                ", High=" + High +
                ", Low=" + Low +
                ", Last=" + Last +
                ", PrevDay=" + PrevDay +
                '}';
    }
}
