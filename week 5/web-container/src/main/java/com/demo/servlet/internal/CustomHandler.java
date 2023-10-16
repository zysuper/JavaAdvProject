package com.demo.servlet.internal;

import com.demo.servlet.HeroServlet;
import com.demo.servlet.Router;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;

import java.util.Optional;

import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpMethod.POST;

/**
 * 默认 handler 将
 *
 * @author zysuper
 */
@ChannelHandler.Sharable
public class CustomHandler extends SimpleChannelInboundHandler<HttpRequest> {
    private final Router router;

    public CustomHandler(Router router) {
        this.router = router;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
        final Optional<HeroServlet> routerOpt = router.findRouter(httpRequest.uri());
        final HeroServlet servlet = routerOpt.isPresent() ? routerOpt.get() : router.defaultRouter();
        if (GET.equals(httpRequest.method())) {
            servlet.doGet(new HttpCustomRequest(httpRequest), new HttpCustomResponse(channelHandlerContext));
        } else if (POST.equals(httpRequest.method())) {
            servlet.doPost(new HttpCustomRequest(httpRequest), new HttpCustomResponse(channelHandlerContext));
        } else {
            router.defaultRouter().doGet(new HttpCustomRequest(httpRequest), new HttpCustomResponse(channelHandlerContext));
        }
    }
}
