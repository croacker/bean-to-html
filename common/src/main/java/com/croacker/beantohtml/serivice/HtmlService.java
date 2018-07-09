package com.croacker.beantohtml.serivice;

import java.lang.reflect.Field;
import java.text.MessageFormat;

/**
 * @author AGumenyuk
 * @since 09.07.2018 18:59
 */
public class HtmlService {

    public byte[] toHtml(Object bean) {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(header())
            .append(startForm());

            for (Field field : bean.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(bean);
                builder.append(field(name, value)).append(br());
            }
            builder.append(endForm());
            builder.append(footer());
        } catch (Exception e) {
            builder = new StringBuilder();
            builder.append(e.getMessage());
            e.printStackTrace();
        }
        return builder.toString().getBytes();
    }

    private String header(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<link rel='stylesheet' href='https://yegor256.github.io/tacit/tacit.min.css'/>\n"+
                "</html>\n" +
                "<body>\n" +
                "<h2>Bean</h2>";
    }

    private String startForm(){
        return "<form><div class='table-container'>";
    }

    private String br(){
        return "<br>";
    }

    private String field(String label, Object value){
        return MessageFormat.format("<div class='column1-container'>{0}:</div>" +
                        "<div class='column2-container'> <input type='text' name='firstname' value=''{1}''</div>",
                label, value);
    }

    private String endForm(){
        return "</div></form>";
    }

    private String footer(){
        return "</body>\n" +
                "</html>";
    }
}
