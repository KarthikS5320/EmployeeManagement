package com.demo.employeeManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DepartmentDTO {

    private String id;
    private String name;
    private Date creationDate;
    private List<EmployeeDTO> employees;
}
