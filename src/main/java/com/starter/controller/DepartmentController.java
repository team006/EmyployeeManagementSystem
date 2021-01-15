package com.starter.controller;

import com.starter.entity.Department;
import com.starter.repository.DepartmentRepository;
import com.starter.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

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
    public String addDepartment(@Valid Department department){
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/departmentDetail/{departmentId}")
    public String employee_Details(@PathVariable Integer departmentId,Model model) {
        model.addAttribute("departmentDetail", departmentService.getDepartmentById(departmentId));
        return "departmentDetail";
    }

    @GetMapping("/departmentDetail/editDepartment/{departmentId}")
        public String editDepartment(@PathVariable Integer departmentId , Model model ){
            model.addAttribute("editDepartment", departmentService.getDepartmentById(departmentId));
            return "update_department";
    }

    @PostMapping("/departmentDetail/editDepartment/{departmentId}")
    public String updateDepartmentDetail(@PathVariable Integer departmentId,
                                         @RequestParam String name,
                                         @RequestParam String short_name){
            Department department = departmentRepository.findOne(departmentId);
            department.setName(name);
            department.setShort_name(short_name);
            departmentRepository.save(department);

        return "redirect:/departmentDetail/{departmentId}" ;
    }

    @PostMapping("/departmentDetail/search_id")
    public String searchDepartmentById(Model model,
                                       @RequestParam("department_id") Integer department_id){

        model.addAttribute("departmentDetail", departmentService.getDepartmentById(department_id));
        if(departmentService.findPKey(department_id))
            return "departmentDetail";
        else
            return "error";
    }

    @RequestMapping("/departmentDetail/delete/{department_id}")
    public String deleteDetails(@PathVariable Integer department_id) {
        departmentService.deleteRecord(department_id);
        return "redirect:/departments";
    }

}
