package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker {
    private String symbol;
    private BigDecimal lastPrice;
    private BigDecimal baseVolume24h;
    private BigDecimal quoteVolume24h;
    private BigDecimal high24h;
    private BigDecimal open24h;
    private BigDecimal low24h;
    private BigDecimal close24h;
    private BigDecimal fluctuation;
    private BigDecimal bestAsk;
    private BigDecimal bestAskSize;
    private BigDecimal bestBid;
    private BigDecimal bestBidSize;
    private String url;
}
