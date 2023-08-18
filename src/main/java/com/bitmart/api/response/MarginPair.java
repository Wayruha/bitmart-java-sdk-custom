package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginPair {
    private String symbol;
    @JsonAlias("max_leverage")
    private Double maxLeverage;
    @JsonAlias("symbol_enabled")
    private Boolean symbolEnabled;
    private MarginAsset base;
    private MarginAsset quote;

    @Data
    public static class MarginAsset {
        @JsonProperty("currency")
        private String asset;
        @JsonAlias("daily_interest")
        private BigDecimal dailyInterest;
        @JsonAlias("hourly_interest")
        private BigDecimal hourlyInterest;
        @JsonAlias("max_borrow_amount")
        private BigDecimal maxBorrowAmount;
        @JsonAlias("min_borrow_amount")
        private BigDecimal minBorrowAmount;
        @JsonAlias("borrowable_amount")
        private BigDecimal borrowableAmount;
    }
}
