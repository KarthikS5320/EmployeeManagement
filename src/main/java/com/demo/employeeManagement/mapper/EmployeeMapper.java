package com.demo.employeeManagement.mapper;


import com.demo.employeeManagement.dto.EmployeeDTO;
import com.demo.employeeManagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeDTO getDTOFromEntity(Employee employee);

    Employee getEntityFromDTO(EmployeeDTO employeeDTO);

    List<EmployeeDTO> mapList(List<Employee> employee);
}