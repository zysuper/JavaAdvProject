package com.demo.servlet;

import com.demo.servlet.internal.Config;
import com.demo.servlet.internal.DefaultRouter;
import com.demo.servlet.internal.NettyHttpServer;
import com.demo.servlet.internal.ServerOptionLoader;

/**
 * 容器启动器.
 *
 * @author zysuper
 */
public class Bootstrap {
    public static HttpServer create() {
        final Config config = ServerOptionLoader.load();
        final DefaultRouter defaultRouter = new DefaultRouter(config);
        return new NettyHttpServer(defaultRouter, config.toServerOption());
    }

    public static void run(String[] args) {
        create().closeWait();
    }
}
