package com.demo.employeeManagement.mapper;
import com.demo.employeeManagement.dto.EmployeeDTO;
import com.demo.employeeManagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    Employee dtoToEntity(EmployeeDTO employeeDTO);

    EmployeeDTO entityToDto(Employee employee);

    List<EmployeeDTO> mapList(List<Employee> employee);
}