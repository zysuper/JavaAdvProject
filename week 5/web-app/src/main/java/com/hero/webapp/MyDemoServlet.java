package com.hero.webapp;

import com.demo.servlet.HeroRequest;
import com.demo.servlet.HeroResponse;
import com.demo.servlet.HeroServlet;

/**
 * 码农实现的自己的 servlet.
 *
 * @author zysuper
 */
public class MyDemoServlet implements HeroServlet {
    @Override
    public void doGet(HeroRequest request, HeroResponse response) throws Exception {
        response.write("Hello, 我自己的 servlet.");
    }

    @Override
    public void doPost(HeroRequest request, HeroResponse response) throws Exception {
        doGet(request, response);
    }
}
