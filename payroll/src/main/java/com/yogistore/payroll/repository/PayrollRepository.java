package com.yogistore.payroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yogistore.payroll.entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {
    @Query("SELECT COALESCE(SUM(p.netSalary), 0) FROM Payroll p")
    Long getTotalNetSalary();

    @Query("SELECT a FROM Payroll a WHERE (:keyword IS NULL OR CONCAT(COALESCE(a.id, ''), ' ', COALESCE(a.month, ''), ' ', COALESCE(a.employee.name, ''), ' ', COALESCE(a.employee.position.positionName, ''), ' ', COALESCE(a.employee.position.basicSalary, ''), ' ', COALESCE(a.transportAllowance, ''), ' ', COALESCE(a.mealAllowance, ''), ' ', COALESCE(a.salaryCut, ''), ' ', COALESCE(a.netSalary, '')) LIKE %:keyword%)")
    List<Payroll> searchAll(@Param("keyword") String keyword);
}
