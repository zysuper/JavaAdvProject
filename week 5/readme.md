# 使用说明

## web-app

码农编写的项目

## web-container

工程师实现的 web server.

## 自定义的 servlet 配置

需要在 server.xml 中配置好你的 servlet 映射.

```xml
<server>
    <port>8080</port>
    <routes>
        <route>
            <!-- uri 地址 -->
            <uri>/aaa/bbb/userServlet.do</uri>
            <!-- 哪个 servlet实现 -->
            <cls>com.hero.webapp.MyDemoServlet</cls>
        </route>
    </routes>
</server>
```