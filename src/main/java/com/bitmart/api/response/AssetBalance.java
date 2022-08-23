package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetBalance {
    @JsonProperty("currency")
    @JsonAlias({"currency", "id" })
    String asset;

    String name;

    @JsonProperty("available")
    BigDecimal free;

    @JsonProperty("frozen")
    BigDecimal locked;
}
