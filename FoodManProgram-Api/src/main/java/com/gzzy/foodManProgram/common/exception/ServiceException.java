package com.gzzy.foodManProgram.common.exception;

public class ServiceException extends RuntimeException {


    private String msg;

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
        this.msg = message;
    }
}
