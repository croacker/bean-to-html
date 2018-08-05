package com.croacker.beantohtml.serivice

import spock.lang.Specification

class BeanServiceTest extends Specification {

    def "Make Bean adapter"(){
        given:
        def beanService = new BeanService();
        def bean = new Object();
        when:
        def adapter = beanService.getAdapter(bean)
        then:
        adapter != null
    }

}
