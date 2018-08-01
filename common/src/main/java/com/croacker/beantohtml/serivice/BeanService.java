package com.croacker.beantohtml.serivice;

import com.croacker.beantohtml.serivice.bean.BeanAdapter;

public class BeanService {

    public <T> BeanAdapter<T> getAdapter(T bean){
        return new BeanAdapter(bean);
    }

}
