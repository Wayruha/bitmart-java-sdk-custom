package com.bitmart.api.response;

import lombok.Data;

@Data
public class Response<T> {
    private String message;
    private int code;
    private String trace;
    private T data;
}
