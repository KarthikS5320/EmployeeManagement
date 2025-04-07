package com.demo.employeeManagement.repository;
import com.demo.employeeManagement.dto.EmployeeNameAndIdDTO;
import com.demo.employeeManagement.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByName(String name);

    Optional<Employee> findByIdNotAndName(String id, String name);

    @Query("SELECT e FROM Employee e WHERE e.deleted = false")
    Page<Employee> findAllEmployee(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findByDepartmentId(String departmentId);
}
