package com.starter.services;

import com.starter.entity.Employee;
import com.starter.repository.AppRepository;
import com.starter.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentEmployeeSerivceImplementation implements DepartmentEmployeeSerivces {

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> findEmpolyeeByDepartmentId(Integer departmentId){
        return appRepository.findEmployeeByDepartment(departmentRepository.findOne(departmentId).getName());
    }



}
