package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {
    @JsonProperty("id")
    private String asset;
    private String name;
    private Boolean withdrawEnabled;
    private Boolean depositEnabled;
}
