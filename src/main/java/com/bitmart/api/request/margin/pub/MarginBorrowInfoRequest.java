package com.bitmart.api.request.margin.pub;

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
public class MarginBorrowInfoRequest extends CloudRequest {
    @ParamKey("symbol")
    private final String symbol;

    public MarginBorrowInfoRequest(String symbol) {
        super("/spot/v1/margin/isolated/pairs", Method.GET, Auth.KEYED);
        this.symbol = symbol;
    }

    public MarginBorrowInfoRequest() {
        this(null);
    }
}
