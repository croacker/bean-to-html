package com.croacker.beantohtml.app;

public class SomeBean {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void incCounter(){
        this.counter++;
    }

    private String id;
    private String name;
    private int counter;
}
