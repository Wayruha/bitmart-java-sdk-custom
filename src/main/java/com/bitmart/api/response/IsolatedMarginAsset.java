package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsolatedMarginAsset {
    @JsonProperty("currency")
    private String asset;
    private boolean borrowEnabled;
    private BigDecimal borrowed;
    private BigDecimal borrowUnpaid;
    private BigDecimal interestUnpaid;
    @JsonProperty("available")
    private BigDecimal free;
    @JsonProperty("frozen")
    private BigDecimal locked;
    private BigDecimal netAsset;
    private BigDecimal netAssetBTC;
    private BigDecimal totalAsset;
}
