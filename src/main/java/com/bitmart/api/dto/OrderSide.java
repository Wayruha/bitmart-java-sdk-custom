package com.bitmart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderSide {
    @JsonProperty("buy")
    BUY("buy"),
    @JsonProperty("sell")
    SELL("sell");

    private String code;

    OrderSide(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
