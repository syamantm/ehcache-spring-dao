package com.syamantakm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Syamantak Mukhopadhyay
 */
public enum SpringFactory {

    INSTANCE;

    private ApplicationContext context;

    private SpringFactory() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public <T> T  getBean(Class<T> tClass) {
        return this.context.getBean(tClass);
    }
}
