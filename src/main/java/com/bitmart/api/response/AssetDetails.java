package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetDetails {
    @JsonProperty("currency")
    private String assetFullName;
    private String name;
    private String network;
    private boolean withdrawEnabled;
    private boolean depositEnabled;

    public String getCanonicalAssetName() {
        return assetFullName.split("-")[0].toUpperCase();
    }
}
