package io.bsnet.breeze.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BreezeBean implements ApplicationContextAware{

    private ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext $ctx) throws BeansException {
        this.ctx = $ctx;

    }
}
