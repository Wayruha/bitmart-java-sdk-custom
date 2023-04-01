package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountWithdrawResponse {
    @JsonProperty("withdraw_id")
    private String withdrawId;
}
