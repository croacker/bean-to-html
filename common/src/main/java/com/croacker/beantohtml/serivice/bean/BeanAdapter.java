package com.croacker.beantohtml.serivice.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanAdapter<T> {

    private T bean;

    private Map<String, FieldAdapter<T>> fields = new HashMap<>();

    public T getBean() {
        return bean;
    }

    public Map<String, FieldAdapter<T>> getFields() {
        return fields;
    }

    public BeanAdapter(T bean) {
        this.bean = bean;
        makeFields(bean);
    }

    public void update(Map<String, String> parameters) {
        for (Map.Entry<String, FieldAdapter<T>> entry : getFields().entrySet()) {
            String key = entry.getKey();
            FieldAdapter field = entry.getValue();
            String newValue = parameters.get(key);
            field.setValue(newValue);
        }
    }

    private void makeFields(T bean) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fields.put(field.getName(), new FieldAdapter<>(bean, field));
        }
    }

}
