package com.demo.servlet.internal;

import com.demo.servlet.HeroResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

/**
 * @author zysuper
 */
public class HttpCustomResponse implements HeroResponse {
    private final ChannelHandlerContext cxt;
    private final DefaultFullHttpResponse response;
    private volatile boolean started;

    public HttpCustomResponse(ChannelHandlerContext cxt) {
        this.cxt = cxt;
        this.response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
    }

    @Override
    public void write(String content) {
        if(!started) {
            response.headers().set(CONTENT_TYPE, TEXT_PLAIN);
            started = true;
        }
        response.content().writeCharSequence(content, StandardCharsets.UTF_8);
        cxt.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
