package com.croacker.beantohtml.serivice.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanAdapter<T> {

    private T bean;

    private Map<String, PlainFieldAdapter<T>> fields = new HashMap<>();

    public T getBean() {
        return bean;
    }

    public Map<String, PlainFieldAdapter<T>> getFields() {
        return fields;
    }

    public BeanAdapter(T bean) {
        this.bean = bean;
        makeFields(bean);
    }

    public String getTitle(){
        return getBean().getClass().getSimpleName();
    }

    public void update(Map<String, String> parameters) {
        for (Map.Entry<String, PlainFieldAdapter<T>> entry : getFields().entrySet()) {
            String key = entry.getKey();
            PlainFieldAdapter field = entry.getValue();
            String newValue = parameters.get(key);
            field.setValue(newValue);
        }
    }

    private void makeFields(T bean) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fields.put(field.getName(), new PlainFieldAdapter<>(bean, field));
        }
    }

}
