package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDepositAddressResponse {
    private String currency;
    private String chain;
    private String address;
    @JsonProperty("address_memo")
    private String addressMemo;
}
