package com.yogistore.payroll.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String gender;
    private String address;

    @ManyToOne
    @JoinColumn(name="position_code", referencedColumnName = "code")
    private Position position;

    
    private String entryDate;
    private String image;
}
