package com.demo.servlet.internal;

import com.demo.servlet.HttpServer;
import com.demo.servlet.Router;
import com.demo.servlet.ServerOption;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * netty 的 web server 实现.
 *
 * @author zysuper
 */
@Slf4j
public class NettyHttpServer implements HttpServer {
    private final AtomicReference<HttpServer.State> state = new AtomicReference(State.STOPPED);
    private final Router router;
    private final ServerOption option;

    private ChannelFuture serverFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private LoggingHandler debugHandler = new LoggingHandler(LogLevel.INFO);

    public NettyHttpServer(Router router, ServerOption option) {
        this.router = router;
        this.option = option;
    }

    @Override
    public State start() {
        if (state.compareAndSet(State.STOPPED, State.STARTING)) {
            this.bossGroup = new NioEventLoopGroup(newThreadFactory("boss"));
            this.workGroup = new NioEventLoopGroup(ioThreadSize(), newThreadFactory("worker"));

            ServerBootstrap b = new ServerBootstrap()
                    .group(this.bossGroup, this.workGroup)
                    .channel(NioServerSocketChannel.class);

            defaultOpt(b);
            pipeline(b);
            listen(b);
        }
        return state.get();
    }


    @Override
    public void shutdown() {
        if (state.compareAndSet(State.STARTED, State.STOPPING)) {
            try {
                if (this.serverFuture != null) {
                    this.serverFuture
                            .channel()
                            .close()
                            .addListener(l -> state.compareAndSet(State.STOPPING, State.STOPPED));
                }
            } finally {
                releaseEventLoopGroup();
            }
        }
    }

    @SneakyThrows
    public void closeWait() {
        this.serverFuture.channel().closeFuture().sync();
    }

    private void releaseEventLoopGroup() {
        this.bossGroup.shutdownGracefully();
        this.workGroup.shutdownGracefully();
    }

    private int ioThreadSize() {
        return (int) (Runtime.getRuntime().availableProcessors() / (1.0F - option.blockRate()));
    }

    private void listen(ServerBootstrap b) {
        try {
            this.serverFuture = b.bind(option.ip(), option.port()).sync();
            log.info("My Netty http Server started on: {}", option.port());
            state.compareAndSet(State.STARTING, State.STARTED);
        } catch (Exception e) {
            log.error("!! bind on {}:{} failed!", option.ip(), option.port());
            // 没有启动成功.
            state.compareAndSet(State.STARTING, State.STOPPED);

            releaseEventLoopGroup();

            throw new RuntimeException(String.format("启动服务器失败, port:%d", option.port()), e);
        }
    }

    private void pipeline(ServerBootstrap b) {
        b.handler(debugHandler);
        final CustomHandler customHandler = new CustomHandler(router);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline()
                        .addLast(debugHandler)
                        .addLast("http-codec", new HttpServerCodec())
                        .addLast("default-servlet", customHandler);
            }
        });
    }

    private static DefaultThreadFactory newThreadFactory(String boss) {
        return new DefaultThreadFactory(boss, true);
    }

    private void defaultOpt(ServerBootstrap b) {
        b.option(NioChannelOption.SO_BACKLOG, option.backlog())
                .childOption(NioChannelOption.TCP_NODELAY, true);
    }
}
