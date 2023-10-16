package com.demo.servlet.internal;

import com.demo.servlet.ServerOption;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "Server")
public class Config {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Route {
        private String uri;
        private Class<?> cls;
    }

    private int port;
    private List<Route> routes;

    public ServerOption toServerOption() {
        return new ServerOption().port(port);
    }
}
