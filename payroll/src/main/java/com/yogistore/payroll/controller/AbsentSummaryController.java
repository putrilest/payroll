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

import com.yogistore.payroll.entity.AbsentSummary;
import com.yogistore.payroll.repository.AbsentSummaryRepository;
import com.yogistore.payroll.repository.EmployeeRepository;

@Controller
public class AbsentSummaryController {
    @Autowired
    private AbsentSummaryRepository absentSummaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping("/absentSummary")
        public String showAbsentSummary(Model model){
        model.addAttribute("absents", absentSummaryRepository.findAll());
        return "absent/show-absent-summary";
    }

    @GetMapping("/addAbsentSummary")
    public String addAbsentSummary(Model model){
        AbsentSummary newAbsentSummary = new AbsentSummary();
        model.addAttribute("newAbsentSummary", newAbsentSummary);
        model.addAttribute("listEmployee", employeeRepository.findAll());
        return "absent/add-absent-summary";
    }

    @PostMapping("/saveAbsentSummary")
    public String saveAbsentSummary(@ModelAttribute AbsentSummary absentSummary){
        absentSummaryRepository.save(absentSummary);
        return "redirect:/absentSummary";
    }

    @GetMapping("/updateAbsentSummary/{id}")
    public String updateAbsentSummary(@PathVariable(value = "id") Integer id, Model model){
        AbsentSummary absentSummary = absentSummaryRepository.getReferenceById(id);
        model.addAttribute("absentSummary", absentSummary);
        model.addAttribute("listEmployee", employeeRepository.findAll());
        return "absent/update-absent-summary";
    }

    @GetMapping("/deleteAbsentSummary/{id}")
    public String deleteAbsentSummary(@PathVariable(value="id") Integer id){
        absentSummaryRepository.deleteById(id);
        return "redirect:/absentSummary";
    }
    
    @GetMapping("/searchAbsen")
    public String searchByName(@RequestParam(name = "search") String name, Model model){
        List<AbsentSummary> absen = absentSummaryRepository.searchAll(name);
        model.addAttribute("absents", absen);
        return "absent/show-absent-summary";
    }
}
