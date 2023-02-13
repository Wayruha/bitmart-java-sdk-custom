package com.bitmart.api.response;

import com.bitmart.api.dto.OrderMode;
import com.bitmart.api.dto.OrderSide;
import com.bitmart.api.dto.OrderStatus;
import com.bitmart.api.dto.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersV2Response {
    @JsonProperty("current_page")
    private Long currentPage;

    private List<Order> orders;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order {
        private String order_id;
        private String symbol;
        private String create_time;
        private OrderSide side;
        private OrderMode order_mode;
        private OrderType type;
        private String price;
        private String price_avg;
        private String size;
        private String notional;
        private String filled_notional;
        private String filled_size;
        private OrderStatus status;
        private String clientOrderId;
    }
}
