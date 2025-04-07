package com.demo.employeeManagement.service;

import com.demo.employeeManagement.dto.DepartmentDTO;
import com.demo.employeeManagement.dto.DepartmentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    DepartmentDTO create(DepartmentDTO departmentDTO);

    DepartmentDTO findById(String id);

    DepartmentResponse findAllDepartment(Pageable pageable);

    boolean delete(String id);

    DepartmentDTO getDepartmentWithEmployees(String departmentId);
}