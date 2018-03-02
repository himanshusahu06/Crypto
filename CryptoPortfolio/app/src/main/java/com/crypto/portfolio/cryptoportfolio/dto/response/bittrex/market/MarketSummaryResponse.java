package com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.market;

import java.util.List;

public class MarketSummaryResponse {

    private Boolean success;
    private String message;
    private List<MarketSummaryDTO> result;

    public MarketSummaryResponse() {
        super();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MarketSummaryDTO> getResult() {
        return result;
    }

    public void setResult(List<MarketSummaryDTO> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MarketSummaryResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
