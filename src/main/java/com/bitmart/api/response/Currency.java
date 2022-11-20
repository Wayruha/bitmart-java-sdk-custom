package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Currency {
    @JsonProperty("id")
    private String asset;
    private String name;
    @JsonProperty("withdraw_enabled")
    private Boolean withdrawEnabled;
    @JsonProperty("deposit_enabled")
    private Boolean depositEnabled;
}
