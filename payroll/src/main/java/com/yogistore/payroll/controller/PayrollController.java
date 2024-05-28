package com.yogistore.payroll.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogistore.payroll.entity.Payroll;
import com.yogistore.payroll.entity.Position;
import com.yogistore.payroll.repository.EmployeeRepository;
import com.yogistore.payroll.repository.PayrollRepository;

@Controller
public class PayrollController {
    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping("/payroll")
    public String showPayroll(Model model){
        model.addAttribute("payrolls", payrollRepository.findAll());
        return "payroll/show-payroll";
    }

    @GetMapping("/addPayroll")
    public String addPayroll(Model model){
        Payroll newPayroll = new Payroll();
        model.addAttribute("newPayroll", newPayroll);
        model.addAttribute("listEmployee", employeeRepository.findAll());
        return "payroll/add-payroll";
    }

    @GetMapping("/findPositionByName")
    @ResponseBody
    public ResponseEntity<Position> findPositionByName(@RequestParam(value = "name") String employeeName) {
        Optional<Position> positionOptional = employeeRepository.findPositionByName(employeeName);
        if (positionOptional.isPresent()) {
            Position position = positionOptional.get();
            return ResponseEntity.ok(position);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/savePayroll")
    public String savePayroll(@ModelAttribute Payroll payroll) {
        payroll.calculateAllowances();
        Long netSalary = payroll.getEmployee().getPosition().getBasicSalary()
                + payroll.getTransportAllowance()
                + payroll.getMealAllowance()
                - payroll.getSalaryCut();

        payroll.setNetSalary(netSalary);

        payrollRepository.save(payroll);

        return "redirect:/payroll";
    }

    @GetMapping("/updatePayroll/{id}")
    public String updatePayroll(@PathVariable(value = "id") Integer id, Model model){
        Payroll payroll = payrollRepository.getReferenceById(id);
        payroll.calculateAllowances();
        model.addAttribute("newPayroll", payroll);
        model.addAttribute("listEmployee", employeeRepository.findAll());
        return "payroll/update-payroll";
    }

    @GetMapping("/deletePayroll/{id}")
    public String deletePayroll(@PathVariable(value="id") Integer id){
        payrollRepository.deleteById(id);
        return "redirect:/payroll";
    }

    @GetMapping("/slipPayroll/{id}")
    public String showSlipPayroll(@PathVariable(value = "id") Integer id, Model model){
        Payroll payroll = payrollRepository.getReferenceById(id);
        model.addAttribute("payroll", payroll);
        return "payroll/slip-payroll";
    }
    
    @GetMapping("/searchPayroll")
    public String searchByName(@RequestParam(name = "search") String name, Model model){
        List<Payroll> absen = payrollRepository.searchAll(name);
        model.addAttribute("payrolls", absen);
        return "payroll/show-payroll";
    }
}
