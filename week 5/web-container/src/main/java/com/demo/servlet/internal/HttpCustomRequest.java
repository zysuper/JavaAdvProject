package com.demo.servlet.internal;

import com.demo.servlet.HeroRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @author zysuper
 */
public class HttpCustomRequest implements HeroRequest {
    private final HttpRequest request;

    public HttpCustomRequest(HttpRequest httpRequest) {
        this.request = httpRequest;
    }

    @Override
    public String getUri() {
        return request.uri();
    }

    @Override
    public String getPath() {
        return new QueryStringDecoder(request.uri()).path();
    }

    @Override
    public String getMethod() {
        return request.method().name();
    }

    @Override
    public Map<String, List<String>> getParameters() {
        return new QueryStringDecoder(request.uri()).parameters();
    }

    @Override
    public List<String> getParameters(String name) {
        return getParameters().get(name);
    }

    @Override
    public String getParameter(String name) {
        return String.join(",", getParameter(name));
    }
}
