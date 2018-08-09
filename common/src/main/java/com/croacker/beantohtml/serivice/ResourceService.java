package com.croacker.beantohtml.serivice;

import java.io.InputStream;

public class ResourceService {

    public InputStream get(String name){
        return getClass().getResourceAsStream(name);
    }

}
