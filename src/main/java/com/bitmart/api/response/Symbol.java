package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Symbol {
    private String symbol;

    private String symbolId;
    @JsonProperty("base_currency")
    private String baseAsset;
    @JsonProperty("quote_currency")
    private String quoteAsset;
    private BigDecimal quoteIncrement;
    private BigDecimal baseMinSize;
    private BigDecimal baseMaxSize;
    private BigDecimal priceMinPrecision;
    private BigDecimal priceMaxPrecision;
    private String expiration;
    private BigDecimal minBuyAmount;
    private BigDecimal minSellAmount;
    private TradeStatus tradeStatus;

    public enum TradeStatus {
        TRADING("trading"),
        PRE_TRADE("pre-trading");

        private final String name;

        @JsonValue
        public String getName() {
            return name;
        }

        TradeStatus(String name) {
            this.name = name;
        }
    }
}
