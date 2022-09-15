package com.bitmart.api.request.account.prv;

import com.bitmart.api.annotations.ParamKey;
import com.bitmart.api.request.Auth;
import com.bitmart.api.request.CloudRequest;
import com.bitmart.api.request.Method;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountMarginAssetTransfer extends CloudRequest {
    @ParamKey("symbol")
    private String symbol;
    @ParamKey("currency")
    private String currency;
    @ParamKey("amount")
    private BigDecimal amount;
    @ParamKey("side")
    private TransferSide side;

    public AccountMarginAssetTransfer() {
        super("/spot/v1/margin/isolated/transfer", Method.POST, Auth.SIGNED);
    }

    @RequiredArgsConstructor
    public enum TransferSide {
        in,
        out;
    }
}
