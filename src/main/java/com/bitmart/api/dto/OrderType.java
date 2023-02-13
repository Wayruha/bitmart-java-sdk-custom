package com.bitmart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderType {
    @JsonProperty("limit")
    LIMIT("limit"),
    @JsonProperty("market")
    MARKET("market");

    private String code;

    OrderType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
