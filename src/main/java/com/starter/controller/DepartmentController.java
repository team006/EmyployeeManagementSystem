package com.starter.controller;

import com.starter.entity.Department;
import com.starter.entity.Employee;
import com.starter.repository.AppRepository;
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
import java.util.List;
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
    @Autowired
    private AppRepository appRepository;

    @GetMapping("/departments")
    public String listDepartment(Model model) {
        model.addAttribute("Department", departmentService.listDepartment());
        return "departments";
    }

    @GetMapping("/addDepartment")
    public String getAddDepartment(Model model) {
        if (!model.containsAttribute("addDepartment")) {
            model.addAttribute("addDepartment", new Department());
        }
        return "addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid Department department,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        departmentValidator.validate(department, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addDepartment", bindingResult);
            redirectAttributes.addFlashAttribute("addDepartment", department);
            return "redirect:/addDepartment";
        }
        departmentService.addDepartment(department);
        return "departments";
    }

    @GetMapping("/departmentDetail/{departmentId}")
    public String employee_Details(@PathVariable Integer departmentId, Model model) {
        model.addAttribute("departmentDetail", departmentService.getDepartmentById(departmentId));
        model.addAttribute("employeeInDepartment", departmentEmployeeSerivce.findEmpolyeeByDepartmentId(departmentId));
        return "departmentDetail";
    }

    @GetMapping("/departmentDetail/editDepartment/{departmentId}")
    public String editDepartment(@PathVariable Integer departmentId, Model model) {
        if (!model.containsAttribute("editDepartment")) {
            model.addAttribute("editDepartment", departmentService.getDepartmentById(departmentId));
        }
        return "update_department";
    }

    @PostMapping("/departmentDetail/editDepartment/{departmentId}")
    public String updateDepartmentDetail(@PathVariable Integer departmentId,
                                         @Valid Department department,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {

        Department departmentName = departmentRepository.findOne(departmentId);
        List<Employee> emps = appRepository.findEmployeeByDepartment(departmentName.getName());

        departmentValidator.nameValidate(department, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editDepartment", bindingResult);
            redirectAttributes.addFlashAttribute("editDepartment", department);
            return "redirect:/departmentDetail/editDepartment/{departmentId}";
        }

        department.setName(department.getName());
        department.setShortName(department.getShortName());
        departmentRepository.save(department);

        for (Employee emp : emps) {
            emp.setDepartment(department.getName());
            appRepository.save(emp);
        }

        return "redirect:/departmentDetail/{departmentId}";
    }

    @PostMapping("/departmentDetail/search_id")
    public String searchDepartmentById(Model model,
                                       @RequestParam("departmentId") Integer departmentId) {

        model.addAttribute("departmentDetail", departmentService.getDepartmentById(departmentId));
        if (departmentService.findPKey(departmentId))
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
