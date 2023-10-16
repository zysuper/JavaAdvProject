package com.demo.servlet.internal;

import com.demo.servlet.ServerOption;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zysuper
 */
public class ServerOptionLoader {
    private static final XmlMapper mapper = new XmlMapper();

    @SneakyThrows
    public static Config load() {
        return mapper.readValue(getServerFile(), Config.class);
    }

    private static InputStream getServerFile() {
        return ServerOptionLoader.class.getClassLoader().getResourceAsStream("server.xml");
    }
}
