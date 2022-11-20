package com.bitmart.websocket;

import com.bitmart.api.common.JsonUtils;
import com.bitmart.api.common.StringCompress;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WebSocketClientHandler<T> extends SimpleChannelInboundHandler {
    private final WebSocketClientHandshaker handShaker;
    private final WebSocketClient<T> webSocketClient;
    private ChannelPromise handshakeFuture;
    private final ObjectMapper mapper;
    private final ObjectReader typeReader;

    public WebSocketClientHandler(WebSocketClientHandshaker handShaker, WebSocketClient<T> webSocketClient) {
        this.handShaker = handShaker;
        this.webSocketClient = webSocketClient;
        this.mapper = new ObjectMapper();
        this.typeReader = mapper.readerFor(new TypeReference<T>() {
        });
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handShaker.handshake(ctx.channel());
        log.debug("WebSocket Client({}) Connecting to {}", webSocketClient.getClientId(), handShaker.uri().toString());
        this.webSocketClient.callBack.onOpen();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("WebSocket Client({}) disconnected! {}", webSocketClient.getClientId(), handShaker.uri().toString());

        if (this.webSocketClient.isClose()) {
            return;
        }
        this.webSocketClient.callBack.onClosed();

        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(webSocketClient::reconnection, 10L, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        if (!handShaker.isHandshakeComplete()) {
            try {
                handShaker.finishHandshake(ch, (FullHttpResponse) msg);
                log.debug("WebSocket Client({}) connected!", webSocketClient.getClientId());
                handshakeFuture.setSuccess();
            } catch (WebSocketHandshakeException e) {
                log.error("WebSocket Client({}) failed to connect", webSocketClient.getClientId());
                handshakeFuture.setFailure(e);
            }
            return;
        }
        final WebSocketCallBack<T> callBack = this.webSocketClient.callBack;
        if (msg instanceof WebSocketFrame) {
            WebSocketFrame frame = (WebSocketFrame) msg;

            if (frame instanceof TextWebSocketFrame) {
                TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
                String message = textFrame.text();
                if (this.webSocketClient.isPrint()) {
                    log.trace("WebSocket Client({}) received message:{}", webSocketClient.getClientId(), message);
                }

                String event = JsonUtils.fromJson(message, "event");
                String errorCode = JsonUtils.fromJson(message, "errorCode");
                if ("login".equals(event) && StringUtils.isNotBlank(errorCode)) {
                    this.webSocketClient.stop();
                    return;
                }

                callBack.onResponse(parseResponse(textFrame.text(), callBack.getType()));
            } else if (frame instanceof BinaryWebSocketFrame) {
                BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
                this.webSocketClient.callBack.onResponse(parseResponse(StringCompress.decode(binaryWebSocketFrame.content()), callBack.getType()));
            } else if (frame instanceof PongWebSocketFrame) {
                // System.out.println("WebSocket Client received pong");

            } else if (frame instanceof CloseWebSocketFrame) {
                log.debug("WebSocket Client({}) received closing", webSocketClient.getClientId());
                ch.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("WebSocket Client({}) exception", webSocketClient.getClientId(), cause);
        cause.printStackTrace();
        if (!handshakeFuture.isDone()) {
            handshakeFuture.setFailure(cause);
        }
        ctx.close();
        webSocketClient.callBack.onFailure(cause);
    }

    private <X> X parseResponse(String json, TypeReference<X> ref) {
        try {
            return mapper.readValue(json, ref);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}