package com.demo.servlet.internal;

import com.demo.servlet.HeroRequest;
import com.demo.servlet.HeroResponse;
import com.demo.servlet.HeroServlet;

/**
 * @author zysuper
 */
public class DefaultCustomServlet implements HeroServlet {
    @Override
    public void doGet(HeroRequest request, HeroResponse response) throws Exception {
        response.write(String.format("404 this Page (%s) not found.", request.getUri()));
    }

    @Override
    public void doPost(HeroRequest request, HeroResponse response) throws Exception {
        doGet(request, response);
    }
}
