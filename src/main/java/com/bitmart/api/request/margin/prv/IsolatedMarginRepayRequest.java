package com.bitmart.api.request.margin.prv;

import com.bitmart.api.annotations.ParamKey;
import com.bitmart.api.request.Auth;
import com.bitmart.api.request.CloudRequest;
import com.bitmart.api.request.Method;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Accessors(chain = true)
public class IsolatedMarginRepayRequest extends CloudRequest {
    @ParamKey("symbol")
    private String symbol;
    @ParamKey("currency")
    private String currency;
    @ParamKey("amount")
    private String amount;

    public IsolatedMarginRepayRequest() {
        super("/spot/v1/margin/isolated/repay", Method.POST, Auth.SIGNED);
    }

    public IsolatedMarginRepayRequest(String symbol, String currency, String amount) {
        this();
        this.symbol = symbol;
        this.currency = currency;
        this.amount = amount;
    }
}
