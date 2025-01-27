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
public class IsolatedMarginWalletRequest extends CloudRequest {
    @ParamKey("symbol")
    private String symbol;

    /**
     * url: GET https://api-cloud.bitmart.com/spot/v1/margin/isolated/account
     * Get Isolated Margin Account Details
     */
    public IsolatedMarginWalletRequest() {
        super("/spot/v1/margin/isolated/account", Method.GET, Auth.KEYED);
    }
}
