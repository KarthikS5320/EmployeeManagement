package com.demo.employeeManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String id;
    private String street;
    private String building;
    private String state;
    private String country;
    private String pinCode;
    private String location;
    private String landmark;
}
