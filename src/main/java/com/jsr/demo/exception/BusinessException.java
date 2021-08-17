package com.jsr.demo.exception;

public class BusinessException extends Exception {
    private final String code;

    private final String defaultMessage;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.defaultMessage = message;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
