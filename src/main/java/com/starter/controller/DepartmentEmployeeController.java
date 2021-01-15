package com.starter.controller;

import com.starter.entity.DepartmentEmployee;
import com.starter.repository.DepartmentEmployeeRepository;
import com.starter.repository.DepartmentRepository;
import com.starter.services.DepartmentEmployeeSerivces;
import com.starter.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
public class DepartmentEmployeeController {

    @Autowired
    private DepartmentEmployeeSerivces departmentEmployeeSerivce;
    @Autowired
    private DepartmentServices departmentServices;
    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

//    @GetMapping("/departmentDetail/{departmentId}")
//    public String getaddEmployee(@PathVariable Integer departmentId,
//                                 Model model){
//        model.addAttribute("addEmployeeInDepartment" , departmentServices.getDepartmentById(departmentId));
//        return "departmentDetail";
//    }

    @PostMapping("/departmentDetail/{departmentId}/addEmployeeInDepartment")
    public String addEmployee(DepartmentEmployee addEmployeeInDepartment,
                              HttpServletRequest request,
                              @PathVariable Integer departmentId){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String empId = request.getParameter("emp-id-in-department");
        int empIdReal = Integer.parseInt(empId);
        addEmployeeInDepartment.setEmp_id(empIdReal);
        addEmployeeInDepartment.setDepartment_id(departmentId);
        addEmployeeInDepartment.setTo_date(null);
        addEmployeeInDepartment.setFrom_date(timestamp);
        departmentEmployeeRepository.save(addEmployeeInDepartment);
        return "departments";
    }
}
