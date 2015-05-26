package io.bsnet.breeze.exception;

/**
 * Created by Administrator on 2015-05-26.
 */
public class BreezeException extends Exception {
    static final long serialVersionUID = 1;
    protected  Object source;

    public BreezeException(Object source) {
        this.source = source;
    }

    public Object getSource(){
        return source;
    }
}
