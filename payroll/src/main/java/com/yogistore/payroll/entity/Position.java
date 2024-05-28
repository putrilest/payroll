package com.yogistore.payroll.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "position")
@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String positionName;
    private Long basicSalary;

}


