package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawQuota {
    private BigDecimal withdrawFee;
    private BigDecimal todayAvailableWithdrawBTC;
    private BigDecimal minWithdraw;
    private int withdrawPrecision;
}
