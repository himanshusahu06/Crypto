package com.crypto.portfolio.cryptoportfolio.dto.response.binance;

/**
 * {
 "symbol": "LTCBTC",
 "orderId": 1,
 "clientOrderId": "myOrder1",
 "price": "0.1",
 "origQty": "1.0",
 "executedQty": "0.0",
 "status": "NEW",
 "timeInForce": "GTC",
 "type": "LIMIT",
 "side": "BUY",
 "stopPrice": "0.0",
 "icebergQty": "0.0",
 "time": 1499827319559
 }
 */
public class OpenOrderDTO {

    private String symbol;
    private Double price;
    private String type;
    private String side;
    private Double origQty;
    private Double executedQty;

    public OpenOrderDTO(){
        super();
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setOrigQty(Double origQty) {
        this.origQty = origQty;
    }

    public void setExecutedQty(Double executedQty) {
        this.executedQty = executedQty;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getSide() {
        return side;
    }

    public Double getOrigQty() {
        return origQty;
    }

    public Double getExecutedQty() {
        return executedQty;
    }

    @Override
    public String toString() {
        return "OpenOrderDTO{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", side='" + side + '\'' +
                ", origQty=" + origQty +
                ", executedQty=" + executedQty +
                '}';
    }
}
