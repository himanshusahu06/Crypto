package com.crypto.portfolio.cryptoportfolio.dto.response.binance;

/**
 * {"asset":"ETH","free":"0.00000000","locked":"0.00000000"}
 */
public class GetBalanceDTO {

    private String asset;
    private Double free;
    private Double locked;

    public GetBalanceDTO() {
        super();
    }

    public String getAsset() {
        return asset;
    }

    public Double getFree() {
        return free;
    }

    public Double getLocked() {
        return locked;
    }

    public Double getTotal() {
        return locked + free;
    }

    @Override
    public String toString() {
        return "GetBalanceDTO{" +
                "asset='" + asset + '\'' +
                ", free=" + free +
                ", locked=" + locked +
                '}';
    }
}
