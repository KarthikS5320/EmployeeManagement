package com.demo.employeeManagement.exception;

import lombok.Getter;

@Getter
public class EMException extends RuntimeException {

    String code;
    String message;
    public String getCode() {
        return code;
    }
    public EMException(String code, String message) {
        super(code + " : " + message);
        this.code = code;
        this.message = message;
    }



    public EMException(String message) {
        super(message);
    }

}