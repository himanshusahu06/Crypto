package com.crypto.portfolio.cryptoportfolio.dto;

/**
 * Created by himanshu.sahu on 28/02/18.
 */

public class ApiError {

    private String clientMessage;
    private String diagnosticMessage;

    public ApiError() {
        super();
    }

    public ApiError(String clientMessage, String diagnosticMessage) {
        this.clientMessage = clientMessage;
        this.diagnosticMessage = diagnosticMessage;
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getDiagnosticMessage() {
        return diagnosticMessage;
    }

    public void setDiagnosticMessage(String diagnosticMessage) {
        this.diagnosticMessage = diagnosticMessage;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "clientMessage='" + clientMessage + '\'' +
                ", diagnosticMessage='" + diagnosticMessage + '\'' +
                '}';
    }
}
