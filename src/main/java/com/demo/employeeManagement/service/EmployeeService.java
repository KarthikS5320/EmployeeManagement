package com.demo.employeeManagement.service;

import com.demo.employeeManagement.dto.EmployeeDTO;
import com.demo.employeeManagement.dto.EmployeeNameAndIdDTO;
import com.demo.employeeManagement.dto.EmployeeResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    EmployeeDTO create(EmployeeDTO employee);

    EmployeeDTO getEmployeeById(String id);

    EmployeeResponse getAllEmployee(Pageable pageable);

    boolean delete(String id);

    List<EmployeeNameAndIdDTO> getEmployeeNamesAndIds();

}