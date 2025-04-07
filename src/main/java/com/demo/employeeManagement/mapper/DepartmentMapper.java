package com.demo.employeeManagement.mapper;


import com.demo.employeeManagement.dto.DepartmentDTO;
import com.demo.employeeManagement.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {

    Department dtoToEntity(DepartmentDTO departmentDTO);

    List<DepartmentDTO> mapList(List<Department> departmentList);

    DepartmentDTO entityToDto(Department department);
}
