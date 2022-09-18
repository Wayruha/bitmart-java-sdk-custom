package com.bitmart.api.request.margin.prv;

import com.bitmart.api.annotations.ParamKey;
import com.bitmart.api.request.Auth;
import com.bitmart.api.request.CloudRequest;
import com.bitmart.api.request.Method;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Accessors(chain = true)
public class IsolatedMarginBorrowRequest extends CloudRequest {
    @ParamKey("symbol")
    private String symbol;

    @ParamKey("currency")
    private String currency;

    @ParamKey("amount")
    private BigDecimal amount;

    public IsolatedMarginBorrowRequest() {
        super("/spot/v1/margin/isolated/borrow", Method.POST, Auth.SIGNED);
    }
}
