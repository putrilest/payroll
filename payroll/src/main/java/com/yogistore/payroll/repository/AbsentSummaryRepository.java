package com.yogistore.payroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yogistore.payroll.entity.AbsentSummary;

public interface AbsentSummaryRepository extends JpaRepository<AbsentSummary, Integer>{
    @Query("SELECT a FROM AbsentSummary a WHERE (:keyword IS NULL OR CONCAT(COALESCE(a.id, ''), ' ', COALESCE(a.month, ''), ' ', COALESCE(a.employee.name, ''), ' ', COALESCE(a.present, ''), ' ', COALESCE(a.sick, ''), ' ', COALESCE(a.excused, ''), ' ', COALESCE(a.unexcused, '')) LIKE %:keyword%)")
    List<AbsentSummary> searchAll(@Param("keyword") String keyword);
}
