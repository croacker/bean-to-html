package com.croacker.beantohtml.serivice.html;

import java.text.MessageFormat;

public class Body {

    public static final String TEMPLATE =
            "<body>" +
                    "<h2>{0}</h2>" +
                    "{1}" +
                    "</body>" +
                    "</html>";

    private String title;

    private Form form;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return MessageFormat.format(TEMPLATE, getForm());
    }
}
