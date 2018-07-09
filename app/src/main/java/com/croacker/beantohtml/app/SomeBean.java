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

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getMounter() {
        return mounter;
    }

    public void setMounter(int mounter) {
        this.mounter = mounter;
    }

    public void incCounter(){
        this.counter++;
    }

    private String id;
    private String name;
    private String last;
    private int counter;
    private int mounter;
}
