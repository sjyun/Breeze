package io.bsnet.breeze.exception;

public class AlreadyExistException extends BreezeException{
    static final long serialVersionUID = 1;
    public AlreadyExistException(Object source) {
        super(source);
    }
}
