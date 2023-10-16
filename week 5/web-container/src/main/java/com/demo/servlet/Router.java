package com.demo.servlet;

import lombok.Data;

import java.util.Optional;

/**
 * 路由接口.
 *
 * @author zysuper
 */
public interface Router {
    /**
     * 注册一个 url 的处理类.
     *
     * @param uri         路由的相对路径.
     * @param heroServlet 处理请求的 servlet.
     */
    void registerServlet(String uri, HeroServlet heroServlet);

    /**
     * 查找 uri 路由的实现的 servlet.
     *
     * @param uri
     * @return
     */
    Optional<HeroServlet> findRouter(String uri);

    /**
     * 返回默认缺省的 servlet 实现
     * @return 缺省 servlet
     */
    HeroServlet defaultRouter();
}
