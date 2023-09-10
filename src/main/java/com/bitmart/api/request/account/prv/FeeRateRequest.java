package com.bitmart.api.request.account.prv;

import com.bitmart.api.annotations.ParamKey;
import com.bitmart.api.request.Auth;
import com.bitmart.api.request.CloudRequest;
import com.bitmart.api.request.Method;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class FeeRateRequest extends CloudRequest {
  @ParamKey("symbol")
  private String symbol;

  public FeeRateRequest(String symbol) {
    super("/spot/v1/trade_fee", Method.GET, Auth.KEYED);
    this.symbol = symbol;
  }
}
