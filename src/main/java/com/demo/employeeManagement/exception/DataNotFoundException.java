package com.demo.employeeManagement.exception;

import lombok.Getter;

@Getter

public class DataNotFoundException extends EMException {
    private String name;

    public DataNotFoundException(String message) {
        super(message);
    }


    public DataNotFoundException(String code, String message) {
        super(code,message);

    }
}

	
    

    