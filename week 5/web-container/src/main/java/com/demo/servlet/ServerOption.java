package com.demo.servlet;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 服务器配置.
 * @author zysuper
 */
@Data
@Accessors(fluent = true, chain = true)
public class ServerOption {
    public static final float DEF_BR = 0.5f;
    public static final String DEF_LIS_IP = "0.0.0.0";
    /**
     * ip.
     */
    private String ip = DEF_LIS_IP;
    /**
     * 阻塞系数.
     */
    private float blockRate = DEF_BR;
    /**
     * backlog 大小.
     */
    private int backlog = 1024;

    private int port;
}
