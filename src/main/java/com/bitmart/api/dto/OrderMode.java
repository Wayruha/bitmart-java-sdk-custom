package com.bitmart.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderMode {
    @JsonProperty("spot")
    SPOT("spot"),
    @JsonProperty("iso_margin")
    ISOLATED_MARGIT("iso_margin"),
    @JsonProperty("all")
    ALL("all");

    private String code;

    OrderMode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
