package com.croacker.beantohtml.serivice.bean;

import java.lang.reflect.Field;

public class FieldAdapter<T> {

    private T bean;

    private Field field;

    public FieldAdapter(T bean, Field field) {
        this.bean = bean;
        this.field = field;
    }

    public String getName() {
        return field.getName();
    }

    public String getValue(){
        String result = "";
        Field field = getField();
        try {
            result = (String) field.get(bean);
        } catch (IllegalAccessException e) {
            panic(e);
        }
        return result;
    }

    public void setValue(String value) {
        Field field = getField();
        try {
            field.set(bean, value);
        } catch (IllegalAccessException e) {
            panic(e);
        }
    }

    private Field getField(){
        return field;
    }

    private void panic(Throwable throwable){
        throwable.printStackTrace();
        throw new RuntimeException(throwable.getMessage(), throwable);
    }
}
