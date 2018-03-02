package com.crypto.portfolio.cryptoportfolio.dto.response.binance;

import java.util.List;

public class GetAccountBalanceResponse {

    private List<GetBalanceDTO> balances;

    public GetAccountBalanceResponse() {
        super();
    }

    public List<GetBalanceDTO> getBalances() {
        return balances;
    }

    public void setBalances(List<GetBalanceDTO> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return "GetAccountBalanceResponse{" +
                "balances=" + balances +
                '}';
    }
}
