package com.croacker.beantohtml.serivice

import spock.lang.Specification

class BeanServiceTest extends Specification {

    def "Adapter for object always equals"(){
        given:
        def beanService = new BeanService()
        def bean = new Object()
        when:
        def adapter1 = beanService.getAdapter(bean)
        def adapter2 = beanService.getAdapter(bean)
        then:
        adapter1 == adapter2
    }

}
