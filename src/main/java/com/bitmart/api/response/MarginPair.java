package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginPair {
    private String symbol;
    private Double maxLeverage;
    private Boolean symbolEnabled;
    private MarginAsset base;
    private MarginAsset quote;

    @Data
    public static class MarginAsset {
        @JsonProperty("currency")
        private String asset;
        private BigDecimal dailyInterest;
        private BigDecimal hourlyInterest;
        private BigDecimal maxBorrowAmount;
        private BigDecimal minBorrowAmount;
        private BigDecimal borrowableAmount;
    }
}
