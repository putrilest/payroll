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

import com.yogistore.payroll.entity.Position;
import com.yogistore.payroll.repository.PositionRepository;

@Controller
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;

    @CrossOrigin
    @GetMapping("/position")
    public String showPosition(Model model){
        model.addAttribute("positions", positionRepository.findAll());
        return "position/show-position";
    }

    @GetMapping("/addPosition")
    public String addPosition(Model model){
        Position newPosition = new Position();
        model.addAttribute("newPosition", newPosition);
        return "position/add-position";
    }

    @PostMapping("/savePosition")
    public String savePosition(@ModelAttribute("position") Position position){
        positionRepository.save(position);
        return "redirect:/position";
    }

    @GetMapping("/updatePosition/{code}")
    public String updatePosition(@PathVariable(value = "code") Integer code, Model model){
        Position position = positionRepository.getReferenceById(code);
        model.addAttribute("position", position);
        return "position/update-position";
    }

    @GetMapping("/deletePosition/{code}")
    public String deletePosition(@PathVariable(value="code") Integer code){
        positionRepository.deleteById(code);
        return "redirect:/position";
    }

    @GetMapping("/searchPost")
    public String searchByName(@RequestParam(name = "search") String name, Model model){
        List<Position> position = positionRepository.searchAll(name);
        model.addAttribute("positions", position);
        return "position/show-position";
    }
}
