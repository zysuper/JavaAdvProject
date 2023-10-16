package com.demo.servlet;


import static org.junit.jupiter.api.Assertions.*;

class BootstrapTest {
    public static void main(String[] args) {
        HttpServer httpServer = Bootstrap.create();
        httpServer.start();
        httpServer.closeWait();
    }
}