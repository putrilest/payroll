package com.yogistore.payroll.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yogistore.payroll.entity.Employee;
import com.yogistore.payroll.entity.Position;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    @Query("SELECT e.position FROM Employee e WHERE e.name = :name")
    Optional<Position> findPositionByName(@Param("name") String name);
    
    @Query("SELECT p FROM Employee p WHERE (:keyword IS NULL OR CONCAT(COALESCE(p.name, ''),' ',COALESCE(p.position.positionName, ''), ' ',COALESCE(p.entryDate,'')) LIKE %:keyword%)")
    List<Employee> searchAll(String keyword);

    List<Employee> findAllByOrderByNameAsc();
}
