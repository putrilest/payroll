package com.yogistore.payroll.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payroll")
@Data
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String month;
  
    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName = "id")
    private Employee employee;

    private Long transportAllowance;
    private Long mealAllowance;
    private Long salaryCut;
    private Long netSalary;

    @PrePersist
    @PreUpdate
    public void calculateAllowances() {
        if (employee != null && employee.getPosition() != null) {
            double basicSalary = employee.getPosition().getBasicSalary();

            // Hitung Meal Allowance (15% dari Gaji Pokok)
            double mealAllowancePercentage = 0.15;
            double calculatedMealAllowance = basicSalary * mealAllowancePercentage;
            setMealAllowance(Math.round(calculatedMealAllowance));

            // Hitung Transport Allowance (10% dari Gaji Pokok)
            double transportAllowancePercentage = 0.10;
            double calculatedTransportAllowance = basicSalary * transportAllowancePercentage;
            setTransportAllowance(Math.round(calculatedTransportAllowance));
        }
    }
}
