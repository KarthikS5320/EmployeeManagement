package com.demo.employeeManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Getter
@Setter
@Where(clause = "deleted = false")
@Table(name = "employee")
public class Employee extends Auditable {

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "salary")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonIgnore
    @Embedded
    private Address address;

    @Column(name = "role")
    private String role;

    @Temporal(TemporalType.DATE)
    @Column(name = "joining_date")
    private Date joiningDate;

    @Column(name = "yearly_bonus_percentage")
    private Double yearlyBonusPercentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee reportingManager;
}