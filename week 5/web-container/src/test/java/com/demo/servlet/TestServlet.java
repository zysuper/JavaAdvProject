package com.demo.servlet;

public class TestServlet implements HeroServlet {
    @Override
    public void doGet(HeroRequest request, HeroResponse response) throws Exception {
        response.write("自定义的 servlet 请求收到东西了！");
    }

    @Override
    public void doPost(HeroRequest request, HeroResponse response) throws Exception {
        doGet(request, response);
    }
}
