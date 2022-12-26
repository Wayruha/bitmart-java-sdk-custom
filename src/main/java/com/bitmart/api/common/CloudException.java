package com.bitmart.api.common;


import lombok.Getter;

public final class CloudException extends Exception {
    @Getter
    private int errorCode;

    public CloudException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CloudException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return super.toString() + ". errorCode=" + errorCode;
    }
}
