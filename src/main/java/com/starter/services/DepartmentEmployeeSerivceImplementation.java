package com.starter.services;


import com.starter.entity.DepartmentEmployee;
import com.starter.repository.DepartmentEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class DepartmentEmployeeSerivceImplementation implements DepartmentEmployeeSerivces {

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Override
    public DepartmentEmployee addEmployee(DepartmentEmployee departmentEmployee){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DepartmentEmployee addDepartmentEmployee = new DepartmentEmployee();
        addDepartmentEmployee.setDepartment_id(departmentEmployee.getDepartment_id());
        addDepartmentEmployee.setEmp_id(departmentEmployee.getEmp_id());
        addDepartmentEmployee.setTo_date(null);
        addDepartmentEmployee.setFrom_date(timestamp);
        return departmentEmployeeRepository.save(departmentEmployee);
    }
}
