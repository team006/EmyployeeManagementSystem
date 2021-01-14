package com.starter.controller;

import com.starter.entity.Department;
import com.starter.services.DepartmentServiceImplementation;
import com.starter.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentService;

    @GetMapping("/departments")
    public String listDepartment(Model model) {
        model.addAttribute("Department", departmentService.listDepartment());
        return "departments";
    }

    @GetMapping("/addDepartment")
    public String getAddDepartment(Model model){
        model.addAttribute("addDepartment" , new Department());
        return "addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid Department department,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }

}
