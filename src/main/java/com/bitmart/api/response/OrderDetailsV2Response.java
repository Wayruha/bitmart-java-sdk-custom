package com.bitmart.api.response;

import com.bitmart.api.dto.OrderMode;
import com.bitmart.api.dto.OrderSide;
import com.bitmart.api.dto.OrderStatus;
import com.bitmart.api.dto.OrderType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDetailsV2Response {
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("client_order_id")
    private String clientOrderId;
    private String symbol;
    @JsonProperty("create_time")
    private String createTime;
    private OrderSide side;
    @JsonProperty("order_mode")
    private OrderMode orderMode;
    private OrderType type;
    private String price;
    @JsonProperty("price_avg")
    private String priceAvg;
    private String size;
    private String notional;
    @JsonProperty("filled_notional")
    private String filledNotional;
    @JsonProperty("filled_size")
    private String filledSize;
    @JsonProperty("unfilled_volume")
    private String unfilledVolume;
    private OrderStatus status;
}
