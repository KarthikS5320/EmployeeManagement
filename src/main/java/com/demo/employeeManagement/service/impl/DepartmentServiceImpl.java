package com.demo.employeeManagement.service.impl;

import com.demo.employeeManagement.dto.DepartmentDTO;
import com.demo.employeeManagement.dto.DepartmentResponse;
import com.demo.employeeManagement.dto.EmployeeDTO;
import com.demo.employeeManagement.dto.EmployeeResponse;
import com.demo.employeeManagement.entity.Department;
import com.demo.employeeManagement.entity.Employee;
import com.demo.employeeManagement.exception.DataNotFoundException;
import com.demo.employeeManagement.exception.DuplicateDataException;
import com.demo.employeeManagement.mapper.DepartmentMapper;
import com.demo.employeeManagement.mapper.EmployeeMapper;
import com.demo.employeeManagement.repository.DepartmentRepository;
import com.demo.employeeManagement.repository.EmployeeRepository;
import com.demo.employeeManagement.service.DepartmentService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);
    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.dtoToEntity(departmentDTO);
        if (isDuplicateName(departmentDTO)) {
            throw new DuplicateDataException("Duplicate Name");
        }
        if (departmentDTO.getId() != null) {
            Optional<Department> departmentOptional = departmentRepository.findById(departmentDTO.getId());
            if (departmentOptional.isPresent()) {
                Department existingDepartment = departmentOptional.get();

                department.setCreatedBy(existingDepartment.getCreatedBy());
                department.setCreatedDate(existingDepartment.getCreatedDate());
                department.setDeleted(existingDepartment.isDeleted());
            } else {
                throw new DataNotFoundException("Data Not Found");
            }
        }
        return departmentMapper.entityToDto(departmentRepository.save(department));
    }

    private boolean isDuplicateName(DepartmentDTO departmentDTO) {
        if (departmentDTO.getId() != null) {
            return this.departmentRepository
                    .findByIdNotAndName(departmentDTO.getId(), departmentDTO.getName().trim()).isPresent();
        } else {
            return this.departmentRepository
                    .findByName(departmentDTO.getName().trim()).isPresent();
        }
    }

    @Override
    public DepartmentDTO findById(String id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            return departmentMapper.entityToDto(departmentOptional.get());
        }
        throw new DataNotFoundException("Data Not Found");
    }

    @Override
    public DepartmentResponse findAllDepartment(Pageable pageable) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        Page<Department> departments = departmentRepository.findAllDepartment(pageable);
        departmentResponse.setDepartment(departmentMapper.mapList(departments.getContent()));
        departmentResponse.setPageCount(departments.getTotalPages());
        departmentResponse.setCount(departments.getTotalElements());

        return departmentResponse;
    }

    @Override
    public boolean delete(String id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            Department department = optional.get();
            department.setDeleted(true);
            departmentRepository.save(department);
        } else {
            throw new DataNotFoundException("Data Not Found");
        }
        return true;
    }
    @Override
    public DepartmentDTO getDepartmentWithEmployees(String departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DataNotFoundException("Department not found"));

        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(employeeMapper::entityToDto)
                .collect(Collectors.toList());

        DepartmentDTO departmentDTO = departmentMapper.entityToDto(department);
        departmentDTO.setEmployees(employeeDTOs);

        return departmentDTO;
    }
}
