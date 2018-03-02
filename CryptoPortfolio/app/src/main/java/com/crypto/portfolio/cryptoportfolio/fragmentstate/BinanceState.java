package com.crypto.portfolio.cryptoportfolio.fragmentstate;

import com.crypto.portfolio.cryptoportfolio.dto.response.binance.GetBalanceDTO;

import java.util.List;

public class BinanceState {

    private String binanceKey;
    private String binanceSecret;
    private List<GetBalanceDTO> balanceDTOList;

    public BinanceState() {
        super();
    }

    public String getBinanceKey() {
        return binanceKey;
    }

    public void setBinanceKey(String binanceKey) {
        this.binanceKey = binanceKey;
    }

    public String getBinanceSecret() {
        return binanceSecret;
    }

    public void setBinanceSecret(String binanceSecret) {
        this.binanceSecret = binanceSecret;
    }

    public List<GetBalanceDTO> getBalanceDTOList() {
        return balanceDTOList;
    }

    public void setBalanceDTOList(List<GetBalanceDTO> balanceDTOList) {
        this.balanceDTOList = balanceDTOList;
    }

    @Override
    public String toString() {
        return "BinanceState{" +
                "binanceKey='" + binanceKey + '\'' +
                ", binanceSecret='" + binanceSecret + '\'' +
                ", balanceDTOList=" + balanceDTOList +
                '}';
    }
}
