package com.bitmart.api.response;

import lombok.Data;

import java.util.List;

@Data
public class AllTickers {
    List<Ticker> tickers;
}
