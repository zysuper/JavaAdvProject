package com.demo.servlet;

/**
 * 定义Servlet规范
 */
public interface HeroServlet {
    void doGet(HeroRequest request, HeroResponse response) throws Exception;

    void doPost(HeroRequest request, HeroResponse response) throws Exception;
}
