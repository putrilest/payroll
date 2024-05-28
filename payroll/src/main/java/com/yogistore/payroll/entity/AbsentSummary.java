package com.yogistore.payroll.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "absent_summary")
@Data
public class AbsentSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String month;

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName = "id")
    private Employee employee;

    private Integer present;
    private Integer sick;
    private Integer excused;
    private Integer unexcused;
}
