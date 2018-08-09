package com.croacker.beantohtml.serivice.html;

public class Head {

    public static final String TEMPLATE =
            "<head>" +
            "<link rel='stylesheet' href='static/css/tacit.min.css'/>" +
            "</head>";

    @Override
    public String toString() {
        return TEMPLATE;
    }

}
