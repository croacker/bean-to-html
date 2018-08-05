package com.croacker.beantohtml.serivice.html;

public class Head {

    public static final String TEMPLATE =
            "<head>" +
            "<link rel='stylesheet' href='https://yegor256.github.io/tacit/tacit.min.css'/>" +
            "</head>";

    @Override
    public String toString() {
        return TEMPLATE;
    }

}
