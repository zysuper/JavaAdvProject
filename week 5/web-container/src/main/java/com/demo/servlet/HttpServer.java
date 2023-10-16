package com.demo.servlet;

/**
 * http 服务器接口.
 *
 * @author zysuper
 */
public interface HttpServer {
    /**
     * 启动接口.
     *
     * @return
     */
    State start();

    /**
     * 停止接口.
     *
     * @return
     */
    void shutdown();

    void closeWait();

    enum State {
        STOPPED, STOPPING, STARTING, STARTED,
    }
}
