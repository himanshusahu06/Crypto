package com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.openorder;


//    "Uuid" : null,
//            "OrderUuid" : "09aa5bb6-8232-41aa-9b78-a5a1093e0211",
//            "Exchange" : "BTC-LTC",
//            "OrderType" : "LIMIT_SELL",
//            "Quantity" : 5.00000000,
//            "QuantityRemaining" : 5.00000000,
//            "Limit" : 2.00000000,
//            "CommissionPaid" : 0.00000000,
//            "Price" : 0.00000000,
//            "PricePerUnit" : null,
//            "Opened" : "2014-07-09T03:55:48.77",
//            "Closed" : null,
//            "CancelInitiated" : false,
//            "ImmediateOrCancel" : false,
//            "IsConditional" : false,
//            "Condition" : null,
//            "ConditionTarget" : null

public class OpenOrderDTO {

    private String Uuid;
    private String OrderUuid;
    private String Exchange;
    private String OrderType;
    private String Quantity;
    private String QuantityRemaining;
    private String Limit;
    private String CommissionPaid;
    private String Price;
    private String PricePerUnit;
    private String Opened;
    private String Closed;
    private String CancelInitiated;
    private String ImmediateOrCancel;
    private String IsConditional;
    private String Condition;
    private String ConditionTarget;

    public OpenOrderDTO() {
        super();
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public String getOrderUuid() {
        return OrderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        OrderUuid = orderUuid;
    }

    public String getExchange() {
        return Exchange;
    }

    public void setExchange(String exchange) {
        Exchange = exchange;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getQuantityRemaining() {
        return QuantityRemaining;
    }

    public void setQuantityRemaining(String quantityRemaining) {
        QuantityRemaining = quantityRemaining;
    }

    public String getLimit() {
        return Limit;
    }

    public void setLimit(String limit) {
        Limit = limit;
    }

    public String getCommissionPaid() {
        return CommissionPaid;
    }

    public void setCommissionPaid(String commissionPaid) {
        CommissionPaid = commissionPaid;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        PricePerUnit = pricePerUnit;
    }

    public String getOpened() {
        return Opened;
    }

    public void setOpened(String opened) {
        Opened = opened;
    }

    public String getClosed() {
        return Closed;
    }

    public void setClosed(String closed) {
        Closed = closed;
    }

    public String getCancelInitiated() {
        return CancelInitiated;
    }

    public void setCancelInitiated(String cancelInitiated) {
        CancelInitiated = cancelInitiated;
    }

    public String getImmediateOrCancel() {
        return ImmediateOrCancel;
    }

    public void setImmediateOrCancel(String immediateOrCancel) {
        ImmediateOrCancel = immediateOrCancel;
    }

    public String getIsConditional() {
        return IsConditional;
    }

    public void setIsConditional(String isConditional) {
        IsConditional = isConditional;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getConditionTarget() {
        return ConditionTarget;
    }

    public void setConditionTarget(String conditionTarget) {
        ConditionTarget = conditionTarget;
    }

    @Override
    public String toString() {
        return "OpenOrderDTO{" +
                "Uuid='" + Uuid + '\'' +
                ", OrderUuid='" + OrderUuid + '\'' +
                ", Exchange='" + Exchange + '\'' +
                ", OrderType='" + OrderType + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", QuantityRemaining='" + QuantityRemaining + '\'' +
                ", Limit='" + Limit + '\'' +
                ", CommissionPaid='" + CommissionPaid + '\'' +
                ", Price='" + Price + '\'' +
                ", PricePerUnit='" + PricePerUnit + '\'' +
                ", Opened='" + Opened + '\'' +
                ", Closed='" + Closed + '\'' +
                ", CancelInitiated='" + CancelInitiated + '\'' +
                ", ImmediateOrCancel='" + ImmediateOrCancel + '\'' +
                ", IsConditional='" + IsConditional + '\'' +
                ", Condition='" + Condition + '\'' +
                ", ConditionTarget='" + ConditionTarget + '\'' +
                '}';
    }
}
