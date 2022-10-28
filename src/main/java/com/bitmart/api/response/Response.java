package com.bitmart.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private String message;
    private int code;
    private String trace;
    private T data;
}
