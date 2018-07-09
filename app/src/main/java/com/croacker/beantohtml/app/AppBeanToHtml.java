package com.croacker.beantohtml.app;

import com.croacker.beantohtml.server.BeanHandler;
import com.croacker.beantohtml.server.BeanHttpServer;
import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class AppBeanToHtml {

    public static void main(String[] args) throws Exception {
        BeanHttpServer server = BeanHttpServer.create(new InetSocketAddress(8084), 0);
        server.createBeanContext("/bean", () -> getBean());
        server.start();
    }

    private static SomeBean getBean(){
        SomeBean someBean = new SomeBean();
        someBean.setId("111");
        someBean.setName("Name");
        someBean.setCounter(0);
        return someBean;
    }

}
