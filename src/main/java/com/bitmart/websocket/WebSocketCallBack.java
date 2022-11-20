package com.bitmart.websocket;

import com.fasterxml.jackson.core.type.TypeReference;

public interface WebSocketCallBack<T> {
    /**
     * Invoked when a text (type {@code 0x1}) message has been received.
     */
    void onResponse(T data);

    TypeReference<T> getType();

    default void onOpen() {}
    default void onClosed() {}
    default void onFailure(Throwable ex) {}
}
