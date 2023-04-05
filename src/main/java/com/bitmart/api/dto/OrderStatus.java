package com.bitmart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStatus {
    @JsonProperty("1")
    ORDER_FAILURE("1"),
    @JsonProperty("2")
    SUCCESS("2"),
    @JsonProperty("3")
    FREEZE_FAILURE("3"),
    @JsonProperty("4")
    FREEZE_SUCCESS("4"),
    @JsonProperty("5")
    PARTIALLY_FILLED("5"),
    @JsonProperty("6")
    FILLED("6"),
    @JsonProperty("7")
    CANCELING("7"),
    @JsonProperty("8")
    CANCELED("8"),
    @JsonProperty("9")
    OUTSTANDING("9"),
    @JsonProperty("10")
    FILLED_AND_CANCELED("10"),
    @JsonProperty("11")
    PARTIALLY_FILLED_AND_CANCELED("11");

    private String code;

    OrderStatus(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
