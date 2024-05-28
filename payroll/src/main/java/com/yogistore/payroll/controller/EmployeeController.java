package com.yogistore.payroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yogistore.payroll.entity.Employee;
import com.yogistore.payroll.repository.EmployeeRepository;
import com.yogistore.payroll.repository.PositionRepository;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @CrossOrigin
    @GetMapping("/employee")
    public String showEmployee(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee/show-employee";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
        Employee newEmployee = new Employee();
        model.addAttribute("newEmployee", newEmployee);
        model.addAttribute("listPosition", positionRepository.findAll());
        return "employee/add-employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable(value = "id") Integer id, Model model){
        Employee employee = employeeRepository.getReferenceById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("listPosition", positionRepository.findAll());
        return "employee/update-employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value="id") Integer id){
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    @GetMapping("search")
    public String searchByName(@RequestParam(name = "search") String name, Model model){
        List<Employee> employees = employeeRepository.searchAll(name);
        model.addAttribute("employees", employees);
        return "employee/show-employee";
    }

    @GetMapping("/detailEmployee/{id}")
    public String showDetailEmployee(@PathVariable(value = "id") Integer id, Model model){
        Employee employee = employeeRepository.getReferenceById(id);
        model.addAttribute("employee", employee);
        return "employee/detail-employee";
    }

        
    @GetMapping("sort-by-name-asc")
    public String sortByNameAsc(Model model){
        List<Employee> employees = employeeRepository.findAllByOrderByNameAsc();
        model.addAttribute("employees", employees);
        return "employee/show-employee";
    }
}
