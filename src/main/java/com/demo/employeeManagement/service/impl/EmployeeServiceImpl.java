package com.demo.employeeManagement.service.impl;

import com.demo.employeeManagement.dto.EmployeeDTO;
import com.demo.employeeManagement.dto.EmployeeNameAndIdDTO;
import com.demo.employeeManagement.dto.EmployeeResponse;
import com.demo.employeeManagement.entity.Employee;
import com.demo.employeeManagement.exception.DataNotFoundException;
import com.demo.employeeManagement.exception.DuplicateDataException;
import com.demo.employeeManagement.mapper.EmployeeMapper;
import com.demo.employeeManagement.repository.DepartmentRepository;
import com.demo.employeeManagement.repository.EmployeeRepository;
import com.demo.employeeManagement.service.EmployeeService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.getEntityFromDTO(employeeDTO);
        if (isDuplicateName(employeeDTO)) {
            throw new DuplicateDataException("Duplicated name");
        }
            if (employeeDTO.getId() != null) {
                Optional<Employee> employeeOptional = employeeRepository.findById(employeeDTO.getId());
                if (employeeOptional.isPresent()) {
                    Employee existingEmployee = employeeOptional.get();

                    employee.setCreatedBy(existingEmployee.getCreatedBy());
                    employee.setCreatedDate(existingEmployee.getCreatedDate());
                    employee.setDeleted(existingEmployee.isDeleted());
                } else {
                    throw new DataNotFoundException("Data Not Found");
                }

            }
        return employeeMapper.getDTOFromEntity(employeeRepository.save(employee));
    }

    private boolean isDuplicateName(EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() != null) {
            return this.employeeRepository
                    .findByIdNotAndName(employeeDTO.getId(), employeeDTO.getName().trim()).isPresent();
        } else {
            return this.employeeRepository
                    .findByName(employeeDTO.getName().trim()).isPresent();
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return employeeMapper.getDTOFromEntity(optionalEmployee.get());
        }
        throw new DataNotFoundException("Data Not Found");
    }

    @Override
    public EmployeeResponse getAllEmployee(Pageable pageable) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        Page<Employee> employees = employeeRepository.findAllEmployee(pageable);
        employeeResponse.setEmployee(employeeMapper.mapList(employees.getContent()));
        employeeResponse.setPageCount(employees.getTotalPages());
        employeeResponse.setCount(employees.getTotalElements());

        return employeeResponse;
    }

    @Override
    public boolean delete(String id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            employee.setDeleted(true);
            employeeRepository.save(employee);
        } else {
            throw new DataNotFoundException("Data Not Found");
        }
        return true;
    }
    @Override
    public List<EmployeeNameAndIdDTO> getEmployeeNamesAndIds() {
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    EmployeeNameAndIdDTO dto = new EmployeeNameAndIdDTO();
                    dto.setId(employee.getId());
                    dto.setName(employee.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}