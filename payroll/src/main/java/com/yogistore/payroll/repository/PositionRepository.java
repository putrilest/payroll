package com.yogistore.payroll.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yogistore.payroll.entity.Position;


public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query("SELECT p FROM Position p WHERE (:keyword IS NULL OR CONCAT(COALESCE(p.code, ''),' ',COALESCE(p.positionName, ''), ' ',COALESCE(p.basicSalary,'')) LIKE %:keyword%)")
    List<Position> searchAll(String keyword);
}

