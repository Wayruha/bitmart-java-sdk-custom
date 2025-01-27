package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker {
    private String symbol;
    @JsonProperty("last_price")
    private BigDecimal lastPrice;
    @JsonProperty("base_volume_24h")
    private BigDecimal baseVolume24h;
    @JsonProperty("quote_volume_24h")
    private BigDecimal quoteVolume24h;
    @JsonProperty("high_24h")
    private BigDecimal high24h;
    @JsonProperty("open_24h")
    private BigDecimal open24h;

    @JsonProperty("low_24h")
    private BigDecimal low24h;

    @JsonProperty("close_24h")
    private BigDecimal close24h;
    private BigDecimal fluctuation;
    @JsonProperty("best_ask")
    private BigDecimal bestAsk;
    @JsonProperty("best_ask_size")
    private BigDecimal bestAskSize;
    @JsonProperty("best_bid")
    private BigDecimal bestBid;
    @JsonProperty("best_bid_size")
    private BigDecimal bestBidSize;
    private String url;
}
