package com.starter.controller;

import com.starter.services.DepartmentServiceImplementation;
import com.starter.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentService;

    @RequestMapping(value = "/departments", method= RequestMethod.GET)
    public String listDepartment(Model model) {
        model.addAttribute("Department", departmentService.listDepartment());
        System.out.println("23");
        return "departments";
    }
}
