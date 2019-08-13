package com.me.testjpa.jpa.util;

public class BadRequestException extends RuntimeException {

    private Object[] args;
    private String objectName;

    public BadRequestException(String objectName, String message) {
        super(message);
        this.objectName = objectName;
        this.args = null;

    }

    public String getObjectName() {
        return objectName;
    }

    public Object[] getArgs() {
        return args;
    }

}
