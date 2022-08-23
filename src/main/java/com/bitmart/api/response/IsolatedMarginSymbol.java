package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsolatedMarginSymbol {
    private String symbol;
    private BigDecimal riskRate;
    private BigDecimal riskLevel;
    private boolean buyEnabled;
    private boolean sellEnabled;
    private BigDecimal liquidateRate;
    private BigDecimal liquidatePrice;
    private IsolatedMarginAsset base;
    private IsolatedMarginAsset quote;
}
