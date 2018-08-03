package com.croacker.beantohtml.serivice;

import com.croacker.beantohtml.serivice.bean.BeanAdapter;

import java.util.HashMap;
import java.util.Map;

public class BeanService {

    private Map<Integer, BeanAdapter> beanCache = new HashMap<>();

    public <T> BeanAdapter<T> getAdapter(T bean){
        BeanAdapter beanAdapter = beanCache.get(bean.hashCode());
        if (beanAdapter == null){
            beanAdapter = new BeanAdapter(bean);
            beanCache.put(bean.hashCode(), beanAdapter);
        }
        return beanAdapter;
    }

}
