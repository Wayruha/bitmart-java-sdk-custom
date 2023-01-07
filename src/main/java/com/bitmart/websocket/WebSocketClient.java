package com.bitmart.websocket;

import com.bitmart.api.common.CloudException;
import com.bitmart.api.common.GlobalConst;
import com.bitmart.api.common.JsonUtils;
import com.bitmart.api.key.CloudKey;
import com.bitmart.api.key.CloudSignature;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import javax.net.ssl.SSLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class WebSocketClient<T> {
    private static final int KEEP_ALIVE_PERIOD_MS = 9_000;
    public static final int MAX_BATCH_SUBSCRIPTION_TOPICS = 20;

    private EventLoopGroup group;
    private Channel clientChannel;
    private CloudKey cloudKey;
    private SslContext sslContext;
    private URI uri;
    private String host;
    private int port;
    private final List<String> reconnectionChannel = new ArrayList<>();
    private boolean reconnectionUseLogin = false;
    private boolean isPrint = true;
    private boolean isClose = false;
    @Getter
    private int clientId;
    private Timer keepAliveTimer;

    public WebSocketCallBack<T> callBack;

    public WebSocketClient(WebSocketCallBack<T> callBack) throws CloudException, URISyntaxException, SSLException {
        init(GlobalConst.CLOUD_WS_URL, null, callBack);
    }


    public WebSocketClient(CloudKey cloudKey, WebSocketCallBack<T> callBack) throws CloudException, URISyntaxException, SSLException {
        init(GlobalConst.CLOUD_WS_URL, cloudKey, callBack);
    }


    public WebSocketClient(String url, WebSocketCallBack<T> callBack) throws CloudException, URISyntaxException, SSLException {
        init(url, null, callBack);
    }


    public WebSocketClient(String url, CloudKey cloudKey, WebSocketCallBack<T> callBack) throws CloudException, URISyntaxException, SSLException {
        init(url, cloudKey, callBack);
    }


    private void init(String url, CloudKey cloudKey, WebSocketCallBack<T> callBack) throws CloudException, URISyntaxException, SSLException {
        this.cloudKey = cloudKey;
        this.callBack = callBack;
        this.uri = new URI(url);
        this.host = uri.getHost() == null ? "127.0.0.1" : uri.getHost();
        this.clientId = (int) (Math.random() * 100);
        String scheme = uri.getScheme() == null ? "ws" : uri.getScheme();

        if (uri.getPort() == -1) {
            if ("ws".equalsIgnoreCase(scheme)) {
                this.port = 80;
            } else if ("wss".equalsIgnoreCase(scheme)) {
                this.port = 443;
            } else {
                this.port = -1;
            }
        } else {
            this.port = uri.getPort();
        }


        if (!"ws".equalsIgnoreCase(scheme) && !"wss".equalsIgnoreCase(scheme)) {
            throw new URISyntaxException(url, "Only WS(S) is supported.");
        }

        final boolean ssl = "wss".equalsIgnoreCase(scheme);
        if (ssl) {
            this.sslContext = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        }

        connection();
    }


    private void connection() throws CloudException {
        try {
            if (this.group == null) {
                this.group = new NioEventLoopGroup();
            }

            final WebSocketClientHandler<T> handler =
                    new WebSocketClientHandler<>(
                            WebSocketClientHandshakerFactory.newHandshaker(
                                    uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()),
                            this);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            if (sslContext != null) {
                                p.addLast(sslContext.newHandler(ch.alloc(), host, port));
                            }
                            p.addLast(
                                    new HttpClientCodec(),
                                    new HttpObjectAggregator(8192),
                                    handler);
                        }
                    });

            this.clientChannel = bootstrap.connect(uri.getHost(), port).sync().channel();

            handler.handshakeFuture().sync();

            keepalive();
        } catch (Exception e) {
            throw new CloudException(-1, e.getMessage());
        }
    }

    public void reconnection() {
        try {
            log.debug("WebSocket Client({}) Reconnection to {}", clientId, this.uri.toString());

            connection();

            if (this.reconnectionUseLogin) {
                this.login();
            }

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
            if (!CollectionUtils.isEmpty(this.reconnectionChannel)) {
                final ArrayList<String> subscriptionChannels = new ArrayList<>(this.reconnectionChannel);
                this.reconnectionChannel.clear();
                log.debug("WebSocket Client({}) re-subscribe channels: {}", clientId, subscriptionChannels);
                this.subscribe(subscriptionChannels);
            }
        } catch (CloudException e) {
            e.printStackTrace();
        }
    }

    public void login() throws CloudException {
        this.reconnectionUseLogin = true;
        CloudSignature.Signature signature = CloudSignature.create(
                "bitmart.WebSocket",
                this.cloudKey.getApiSecret(),
                this.cloudKey.getMemo());

        OpParam opParam = new OpParam().setOp("login").setArgs(ImmutableList.of(
                this.cloudKey.getApiKey(),
                signature.getTimestamp(),
                signature.getSign()
        ));

        String param = JsonUtils.toJson(opParam);
        if (isPrint) {
            log.debug("WebSocket Client({}) Send:{}", clientId, param);
        }

        this.clientChannel.writeAndFlush(new TextWebSocketFrame(param));

    }

    public void subscribe(List<String> channels) {
        this.reconnectionChannel.addAll(channels);
        Lists.partition(channels, MAX_BATCH_SUBSCRIPTION_TOPICS).forEach(this::_subscribe);
    }

    private void _subscribe(List<String> channels) {
        if (channels.size() > MAX_BATCH_SUBSCRIPTION_TOPICS)
            throw new IllegalArgumentException("Subscriptions batch size violated: " + channels.size() + "; max: " + MAX_BATCH_SUBSCRIPTION_TOPICS);
        final OpParam opParam = new OpParam().setOp("subscribe").setArgs(channels);
        final String param = JsonUtils.toJson(opParam);
        if (isPrint) {
            log.debug("WebSocket Client({}) Send:{}", clientId, param);
        }

        this.clientChannel.writeAndFlush(new TextWebSocketFrame(param));
    }

    private void keepalive() {
        Channel channel = this.clientChannel;
        if (keepAliveTimer != null) {
            keepAliveTimer.cancel();
        }
        keepAliveTimer = new Timer("WebSocket-Keepalive");
        keepAliveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (channel.isActive()) {
                    log.debug("WebSocket Client({}) KeepAlive", clientId);
                    channel.writeAndFlush(new PingWebSocketFrame(Unpooled.wrappedBuffer(new byte[]{8, 1, 8, 1})));
                }
            }
        }, 2000, KEEP_ALIVE_PERIOD_MS);
    }

    public void stop() {
        log.debug("WebSocket Client({}) stop.", clientId);
        this.isClose = true;
        this.clientChannel.close();
        this.group.shutdownGracefully();
    }

    public void setIsPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }

    public boolean isPrint() {
        return this.isPrint;
    }

    public boolean isClose() {
        return this.isClose;
    }
}

