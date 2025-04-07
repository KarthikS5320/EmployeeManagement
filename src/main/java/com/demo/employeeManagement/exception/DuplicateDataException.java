package com.demo.employeeManagement.exception;

import lombok.Getter;

@Getter
public class DuplicateDataException extends EMException {
    private String name;
    public DuplicateDataException(String code,String message) {
        super(code,message);
    }
    public DuplicateDataException(String message) {
        super(message);
    }
}
