package com.starter.services;

import com.starter.entity.Department;
import com.starter.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImplementation implements DepartmentServices {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> listDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        return departmentRepository.findOne(departmentId);
    }

    @Override
    public Department addDepartment(Department department){
        Department addDepartment = new Department();
        addDepartment.setDepartment_id(department.getDepartment_id());
        addDepartment.setName(department.getName());
        addDepartment.setShort_name(department.getShort_name());
        return departmentRepository.save(department);
    }


}
