package com.croacker.beantohtml.serivice;

import com.croacker.beantohtml.serivice.bean.BeanAdapter;
import com.croacker.beantohtml.serivice.bean.FieldAdapter;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author croacker
 * @since 09.07.2018 18:59
 */
public class HtmlService {

    private BeanService beanService;

    private BeanService getBeanService(){
        if(beanService == null){
            beanService = new BeanService();
        }
        return beanService;
    }

    public <T> byte[] toHtml(T bean) {
        BeanAdapter<T> beanAdapter = getBeanService().getAdapter(bean);
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(header(bean)).append(startForm());
            for (FieldAdapter field : beanAdapter.getFields().values()) {
                builder.append(field(field.getName(), field.getValue())).append(br());
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

    public <T> void toBean(T bean, Map<String, String> parameters) {
        BeanAdapter<T> beanAdapter = getBeanService().getAdapter(bean);
        beanAdapter.update(parameters);
    }

    private <T> String header(T bean){
        return "<!DOCTYPE html>\n" +
                "<head>\n" +
                "<link rel='stylesheet' href='https://yegor256.github.io/tacit/tacit.min.css'/>\n"+
                "</head>\n" +
                "<body>\n" +
                "<h2>" + bean.getClass().getSimpleName() + "</h2>";
    }

    private String startForm(){
        return "<form method='post'><div class='table-container'>";
    }

    private String br(){
        return "<br>";
    }

    private String field(String label, Object value){
        return MessageFormat.format("<div class='column1-container'>{0}:</div>" +
                        "<div class='column2-container'>" +
                        " <input type='text' autocomplete='off' name=''{0}'' value=''{1}''/>" +
                        "</div>",
                label, value);
    }

    private String endForm(){
        return "</div>" +
                "<div><input type='submit'></div>" +
                "</form>";
    }

    private String footer(){
        return "</body>\n" +
                "</html>";
    }
}
