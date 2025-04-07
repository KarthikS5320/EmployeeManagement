package com.demo.employeeManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDTO {

    private String id;

    private String name;

    private Date dateOfBirth;

    private Double salary;

    private AddressDTO address;

    private String role;

    private Date joiningDate;

    private Double yearlyBonusPercentage;

    private DepartmentDTO department;

    private EmployeeDTO reportingManager;
}