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
        addDepartment.setDepartmentId(department.getDepartmentId());
        addDepartment.setName(department.getName());
        addDepartment.setShortName(department.getShortName());
        return departmentRepository.save(department);
    }

    @Override
    public boolean findPKey(Integer departmentId) {
        boolean department_exist;
        department_exist=departmentRepository.exists(departmentId);
        return department_exist;
    }

    @Override
    public void deleteRecord(Integer departmentId) {
        departmentRepository.delete(departmentId);
    }


}
