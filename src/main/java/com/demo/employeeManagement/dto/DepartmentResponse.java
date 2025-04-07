package com.demo.employeeManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentResponse {

    private Long count;
    private Integer pageCount;
    private List<DepartmentDTO> department;

}
