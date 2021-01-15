package com.starter.controller;

import com.starter.entity.Department;
import com.starter.repository.DepartmentEmployeeRepository;
import com.starter.repository.DepartmentRepository;
import com.starter.services.DepartmentEmployeeSerivceImplementation;
import com.starter.services.DepartmentEmployeeSerivces;
import com.starter.services.DepartmentServices;
import com.starter.validator.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentEmployeeSerivces departmentEmployeeSerivce;
    @Autowired
    private DepartmentValidator departmentValidator;

    @GetMapping("/departments")
    public String listDepartment(Model model) {
        model.addAttribute("Department", departmentService.listDepartment());
        return "departments";
    }

    @GetMapping("/addDepartment")
    public String getAddDepartment(Model model){
        if(!model.containsAttribute("addDepartment")) {
            model.addAttribute("addDepartment", new Department());
        }
        return "addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid Department department,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){
        departmentValidator.validate(department,bindingResult);
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addDepartment" , bindingResult);
            redirectAttributes.addFlashAttribute("addDepartment", department);
            return  "redirect:/addDepartment";
        }
        departmentService.addDepartment(department);
        return "departments";
    }

    @GetMapping("/departmentDetail/{departmentId}")
    public String employee_Details(@PathVariable Integer departmentId,Model model) {
        model.addAttribute("departmentDetail", departmentService.getDepartmentById(departmentId));
        model.addAttribute("employeeInDepartment",departmentEmployeeSerivce.findEmpolyeeByDepartmentId(departmentId));
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
                                         @RequestParam String shortName){
            Department department = departmentRepository.findOne(departmentId);
            department.setName(name);
            department.setShortName(shortName);
            departmentRepository.save(department);

        return "redirect:/departmentDetail/{departmentId}" ;
    }

    @PostMapping("/departmentDetail/search_id")
    public String searchDepartmentById(Model model,
                                       @RequestParam("departmentId") Integer departmentId){

        model.addAttribute("departmentDetail", departmentService.getDepartmentById(departmentId));
        if(departmentService.findPKey(departmentId))
            return "departmentDetail";
        else
            return "error";
    }

    @RequestMapping("/departmentDetail/delete/{departmentId}")
    public String deleteDetails(@PathVariable Integer departmentId) {
        departmentService.deleteRecord(departmentId);
        return "redirect:/departments";
    }

}
