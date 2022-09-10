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

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Accessors(chain = true)
@Builder
public class MarginOrderRequest extends CloudRequest {
    @ParamKey("symbol")
    private String symbol;      //Trading pair (e.g. BTC_USDT)

    @ParamKey("side")
    private String side;        //buy or sell

    @ParamKey("type")
    private String type;        //limit/market/limit_maker/ioc

    @ParamKey("size")
    private String size;        //Order size

    @ParamKey("price")
    private String price;       //Price

    @ParamKey("notional")
    private String notional;    //Quantity bought, required when buying at market price

    @ParamKey("clientOrderId")
    private String clientOrderId; //Client-defined OrderId(A combination of numbers and letters, less than 32 bits)

    public MarginOrderRequest() {
        super("spot/v1/margin/submit_order", Method.POST, Auth.SIGNED);
    }

    public MarginOrderRequest(String symbol, String side, String type, String size, String price, String notional, String clientOrderId) {
        this();
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.size = size;
        this.price = price;
        this.notional = notional;
        this.clientOrderId = clientOrderId;
    }
}
