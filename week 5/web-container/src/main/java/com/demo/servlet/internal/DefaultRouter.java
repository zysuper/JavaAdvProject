package com.demo.servlet.internal;

import cn.hutool.core.util.ReflectUtil;
import com.demo.servlet.HeroServlet;
import com.demo.servlet.Router;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zysuper
 */
public class DefaultRouter implements Router {
    private final Map<String, HeroServlet> mapper = new ConcurrentHashMap<>(10);
    private final HeroServlet defaultServlet = new DefaultCustomServlet();
    private final Config config;

    public DefaultRouter(Config config) {
        this.config = config;
        fromConfig(config);
    }

    private void fromConfig(Config config) {
        for (Config.Route route : config.getRoutes()) {
            mapper.put(route.getUri(), (HeroServlet) ReflectUtil.newInstance(route.getCls()));
        }
    }

    @Override
    public void registerServlet(String uri, HeroServlet heroServlet) {
        this.mapper.put(uri, heroServlet);
    }

    @Override
    public Optional<HeroServlet> findRouter(String uri) {
        return Optional.ofNullable(this.mapper.get(uri));
    }

    @Override
    public HeroServlet defaultRouter() {
        return defaultServlet;
    }
}
