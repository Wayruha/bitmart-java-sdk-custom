package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserFeeRateResponse {
  private String symbol;
  @JsonProperty("buy_maker_fee_rate")
  private BigDecimal buyMakerFeeRate;
  @JsonProperty("buy_taker_fee_rate")
  private BigDecimal buyTakerFeeRate;
  @JsonProperty("sell_maker_fee_rate")
  private BigDecimal sellMakerFeeRate;
  @JsonProperty("sell_taker_fee_rate")
  private BigDecimal sellTakerFeeRate;
}
