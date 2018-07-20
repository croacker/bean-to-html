package com.croacker.beantohtml.app;

import com.croacker.beantohtml.server.BeanHttpServer;

import java.net.InetSocketAddress;

public class AppBeanToHtml {

    public static void main(String[] args) throws Exception {
        BeanHttpServer server = BeanHttpServer.create(new InetSocketAddress(8084), 0);
        server.createBeanContext("/bean", () -> getBean());
        server.start();
    }

    private static SomeBean getBean() {
        SomeBean someBean = new SomeBean();
        someBean.setId("111");
        someBean.setName("Name");
        return someBean;
    }

}
