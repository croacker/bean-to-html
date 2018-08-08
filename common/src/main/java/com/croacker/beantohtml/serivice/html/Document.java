package com.croacker.beantohtml.serivice.html;

import java.text.MessageFormat;

public class Document {

    public static final String TEMPLATE = "<!DOCTYPE html>" +
            "{0}" +
            "{1}" +
            "</html>";

    private Head head;

    private Body body;

    public Head getHead() {
        if(head == null){
            head = new Head();
        }
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString(){
        return MessageFormat.format(TEMPLATE, getHead(), getBody());
    }
}
