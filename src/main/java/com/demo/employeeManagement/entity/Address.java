package com.demo.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;



@Entity
@Getter
@Setter
@Where(clause = "deleted = false")
@Table(name = "address")
public class Address extends Auditable {
    @Column(name = "building")
    private String building;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "pincode")
    private String pincode;
}
