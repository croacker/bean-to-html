package com.croacker.beantohtml.serivice.bean;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class BeanAdapter<T> {

    private T bean;

    private List<FieldAdapter<T>> fields = new LinkedList<>();

    public T getBean() {
        return bean;
    }

    public List<FieldAdapter<T>> getFields() {
        return fields;
    }

    public BeanAdapter(T bean) {
        this.bean = bean;
        makeFields(bean);
    }

    private void makeFields(T bean) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fields.add(new FieldAdapter<>(bean, field));
        }
    }
}
