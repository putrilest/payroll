package com.yogistore.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yogistore.payroll.repository.EmployeeRepository;
import com.yogistore.payroll.repository.PayrollRepository;
import com.yogistore.payroll.repository.PositionRepository;

@Controller
public class HomeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PayrollRepository payrollRepository;

    @GetMapping("/")
    public String showHome(Model  model) {
        long totalEmployees = employeeRepository.count();
        long totalPositions = positionRepository.count();
        long totalNetSalary = payrollRepository.getTotalNetSalary();
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("totalPositions", totalPositions);
        model.addAttribute("totalNetSalary", totalNetSalary);
        return "home";
    }

    @GetMapping("/information")
    public String showInformasiPage() {
        return "information"; 
    }
}









