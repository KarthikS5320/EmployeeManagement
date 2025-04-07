package com.demo.employeeManagement.dto;

import com.demo.employeeManagement.entity.Employee;
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
    private EmployeeDTO departmentHead;
    private List<EmployeeDTO> employees;
}
