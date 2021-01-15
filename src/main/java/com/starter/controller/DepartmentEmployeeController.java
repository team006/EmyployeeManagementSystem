package com.starter.controller;

import com.starter.entity.Department;
import com.starter.entity.DepartmentEmployee;
import com.starter.entity.Employee;
import com.starter.repository.AppRepository;
import com.starter.repository.DepartmentEmployeeRepository;
import com.starter.repository.DepartmentRepository;
import com.starter.services.DepartmentEmployeeSerivces;
import com.starter.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.function.Function;

@Controller
public class DepartmentEmployeeController {

    @Autowired
    private DepartmentEmployeeSerivces departmentEmployeeSerivce;
    @Autowired
    private DepartmentServices departmentServices;
    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    @PostMapping("/departmentDetail/{departmentId}/addEmployeeInDepartment")
    public String addEmployee(DepartmentEmployee addEmployeeInDepartment,
                              HttpServletRequest request,
                              @PathVariable Integer departmentId){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String empId = request.getParameter("emp-id-in-department");
        int empIdReal = Integer.parseInt(empId);
        addEmployeeInDepartment.setEmpId(empIdReal);
        addEmployeeInDepartment.setDepartmentId(departmentId);
        addEmployeeInDepartment.setTo_date(null);
        addEmployeeInDepartment.setFrom_date(timestamp);

        String name = departmentRepository.findOne(departmentId).getName();
        Employee emp = appRepository.findOne(empIdReal);
        emp.setDepartment(name);
        appRepository.save(emp);
        departmentEmployeeRepository.save(addEmployeeInDepartment);
        return "redirect:/departmentDetail/{departmentId}";
    }
    @PostMapping("/departmentDetail/{departmentId}/moveEmployee")
    public String moveEmployee(@PathVariable Integer departmentId,
                               @RequestParam Integer empId,
                               @RequestParam Integer newDepartmentId){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DepartmentEmployee departmentEmployee = departmentEmployeeRepository.findByEmpId(empId);
        departmentEmployee.setDepartmentId(newDepartmentId);
        departmentEmployee.setFrom_date(timestamp);
        departmentEmployeeRepository.save(departmentEmployee);



        String name = departmentRepository.findOne(newDepartmentId).getName();
        Employee emp = appRepository.findOne(empId);
        emp.setDepartment(name);
        appRepository.save(emp);

        return "redirect:/departmentDetail/{departmentId}";
    }

    @RequestMapping("/departmentDetail/resign/{employeeId}")
    public String resignEmployee(@PathVariable Integer employeeId) {
        DepartmentEmployee departmentEmployee = departmentEmployeeRepository.findByEmpId(employeeId);
        departmentEmployeeRepository.delete(departmentEmployee.getDepartmentEmpId());
        appRepository.delete(employeeId);
        return "redirect:/departmentDetail/{departmentId}";
    }
}
