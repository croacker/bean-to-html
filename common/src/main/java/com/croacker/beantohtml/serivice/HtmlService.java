package com.croacker.beantohtml.serivice;

import com.croacker.beantohtml.serivice.bean.BeanAdapter;
import com.croacker.beantohtml.serivice.bean.FieldAdapter;
import com.croacker.beantohtml.serivice.html.*;

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
            builder.append(getDocument(beanAdapter));
        } catch (Exception e) {
            builder = new StringBuilder();
            builder.append(e.getMessage());
            e.printStackTrace();
        }
        return builder.toString().getBytes();
    }

    private <T> Document getDocument(BeanAdapter<T> beanAdapter) {
        Document document = new Document();
        document.setHead(new Head());
        Body body = new Body();
        document.setBody(body);
        body.setTitle(beanAdapter.getTitle());

        Form form = new Form();
        body.setForm(form);

        for(String fieldName : beanAdapter.getFields().keySet()){
            FieldAdapter fieldAdapter = beanAdapter.getFields().get(fieldName);
            FormField formField = new FormField(fieldAdapter.getName(), fieldAdapter.getValue());
            form.addField(formField);
        }
        return document;
    }

    public <T> void toBean(T bean, Map<String, String> parameters) {
        BeanAdapter<T> beanAdapter = getBeanService().getAdapter(bean);
        beanAdapter.update(parameters);
    }

}
