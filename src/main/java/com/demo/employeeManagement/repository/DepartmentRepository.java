package com.demo.employeeManagement.repository;

import com.demo.employeeManagement.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    Optional<Department> findByName(String name);

    Optional<Department> findByIdNotAndName(String id, String name);

    @Query("SELECT d FROM Department d WHERE d.deleted = false")
    Page<Department> findAllDepartment(Pageable pageable);
}
