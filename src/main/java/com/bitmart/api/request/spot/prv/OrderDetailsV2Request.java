package com.bitmart.api.request.spot.prv;

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
public class OrderDetailsV2Request extends CloudRequest {
    @ParamKey("order_id")
    private String orderId;

    public OrderDetailsV2Request() {
        super("/spot/v2/order_detail", Method.GET, Auth.KEYED);
    }
}
