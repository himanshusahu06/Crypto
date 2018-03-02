package com.crypto.portfolio.cryptoportfolio.fragmentstate;

import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.account.GetBalanceDTO;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.market.MarketSummaryDTO;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.openorder.OpenOrderDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BittrexState {

    private Double totalBTC;
    private String bittrexKey;
    private String bittrexSecret;
    private List<GetBalanceDTO> getBalanceDTO;
    private List<OpenOrderDTO> openOrderDTO;
    private Map<String, MarketSummaryDTO> marketSummaryMap;

    public BittrexState() {
        super();
    }

    public String getBittrexKey() {
        return bittrexKey;
    }

    public void setBittrexKey(String bittrexKey) {
        this.bittrexKey = bittrexKey;
    }

    public String getBittrexSecret() {
        return bittrexSecret;
    }

    public void setBittrexSecret(String bittrexSecret) {
        this.bittrexSecret = bittrexSecret;
    }

    public List<GetBalanceDTO> getGetBalanceDTO() {
        return getBalanceDTO;
    }

    public void setGetBalanceDTO(List<GetBalanceDTO> getBalanceDTO) {
        this.getBalanceDTO = getBalanceDTO;
    }

    public List<OpenOrderDTO> getOpenOrderDTO() {
        return openOrderDTO;
    }

    public void setOpenOrderDTO(List<OpenOrderDTO> openOrderDTO) {
        this.openOrderDTO = openOrderDTO;
    }

    public Map<String, MarketSummaryDTO> getMarketSummaryMap() {
        return marketSummaryMap;
    }

    public void setMarketSummaryMap(Map<String, MarketSummaryDTO> marketSummaryMap) {
        this.marketSummaryMap = marketSummaryMap;
    }

    private MarketSummaryDTO getPrice(String key) {
        String newKey = "BTC-" + key;
        return marketSummaryMap.get(newKey);
    }
    public List<GetBalanceDTO> filterAndSetPrice(List<GetBalanceDTO> getBalanceDTOList) {
        List<GetBalanceDTO> filteredGetBalanceDTOList = new ArrayList<>();
        Double totalBtc = 0.0;
        for (GetBalanceDTO getBalanceDTO : getBalanceDTOList) {
            if (getBalanceDTO.getBalance() >= 1e-6) {
                MarketSummaryDTO marketSummaryDTO = getPrice(getBalanceDTO.getCurrency());
                if (marketSummaryDTO != null) {
                    getBalanceDTO.setCurrentPrice(marketSummaryDTO.getLast());
                    totalBtc += getBalanceDTO.getCurrentPrice() * getBalanceDTO.getBalance();
                } else {
                    // BTC-BTC case
                    totalBtc += getBalanceDTO.getBalance();
                }
                filteredGetBalanceDTOList.add(getBalanceDTO);
            }
        }
        setTotalBTC(totalBtc);
        return filteredGetBalanceDTOList;
    }

    public Double getTotalBTC() {
        return totalBTC;
    }

    public void setTotalBTC(Double totalBTC) {
        this.totalBTC = totalBTC;
    }

    @Override
    public String toString() {
        return "BittrexState{" +
                "totalBTC=" + totalBTC +
                ", bittrexKey='" + bittrexKey + '\'' +
                ", bittrexSecret='" + bittrexSecret + '\'' +
                ", getBalanceDTO=" + getBalanceDTO +
                ", openOrderDTO=" + openOrderDTO +
                ", marketSummaryMap=" + marketSummaryMap +
                '}';
    }
}
