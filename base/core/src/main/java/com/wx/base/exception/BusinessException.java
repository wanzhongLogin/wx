package com.wx.base.exception;

public class BusinessException extends RuntimeException{

    private String message;

    public BusinessException(String error) {
        super(error);
        this.message = null;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
