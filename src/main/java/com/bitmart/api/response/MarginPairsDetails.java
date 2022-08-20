package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginPairsDetails {
    List<MarginPair> symbols;
}
