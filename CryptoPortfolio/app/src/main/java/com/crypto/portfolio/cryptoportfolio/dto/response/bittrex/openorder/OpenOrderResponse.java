package com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.openorder;

import com.crypto.portfolio.cryptoportfolio.dto.ApiError;

import java.util.List;

public class OpenOrderResponse {

    private Boolean success;
    private String message;
    private List<OpenOrderDTO> result;
    private ApiError apiError;

    public OpenOrderResponse() {
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

    public List<OpenOrderDTO> getResult() {
        return result;
    }

    public void setResult(List<OpenOrderDTO> result) {
        this.result = result;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    @Override
    public String toString() {
        return "OpenOrderResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", apiError=" + apiError +
                '}';
    }
}
