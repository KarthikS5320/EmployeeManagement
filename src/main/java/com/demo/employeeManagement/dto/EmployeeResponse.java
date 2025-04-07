package com.demo.employeeManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EmployeeResponse {

    private Long count;
    private Integer pageCount;
    private List<EmployeeDTO> employee;

}
