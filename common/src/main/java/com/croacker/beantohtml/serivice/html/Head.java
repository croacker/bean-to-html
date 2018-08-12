package com.croacker.beantohtml.serivice.html;

import java.text.MessageFormat;

public class Head {

    public static final String TEMPLATE =
            "<head>" +
            "<link rel='stylesheet' href=\"{0}/css/tacit.min.css\"/>" +
            "</head>";

    private String staticHandler;

    public Head(String staticHandler) {
    this.staticHandler = staticHandler;
    }

    @Override
    public String toString() {
        return MessageFormat.format(TEMPLATE, staticHandler);
    }

}
