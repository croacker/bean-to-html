package com.croacker.beantohtml.app;

import com.croacker.beantohtml.server.BeanHttpServer;

import java.net.InetSocketAddress;

public class AppBeanToHtml {

    public static void main(String[] args) throws Exception {
        BeanHttpServer server = BeanHttpServer.create(new InetSocketAddress(8084), 0);
        Employee bean = getBean();
        server.createBeanContext("/bean", () -> bean);
        server.start();
    }

    private static Employee getBean() {
        Employee employee = new Employee();
        employee.setId("222");
        employee.setFirstName("Name");
        return employee;
    }

}
