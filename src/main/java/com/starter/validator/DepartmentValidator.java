package com.starter.validator;

import com.starter.entity.Department;
import com.starter.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department department = (Department) target;

        Department checkDepartmentId = departmentRepository.findOne(department.getDepartmentId());
        if (checkDepartmentId != null){
            errors.rejectValue("departmentId",null , "Department ID นี้มีผู้ใช้งานแล้ว");
        }

        Department checkDepartmentName = departmentRepository.findByName(department.getName());
        if (checkDepartmentName != null){
            errors.rejectValue("name",null , "ชื่อ Department นี้มีผู้ใช้งานแล้ว");
        }

        Department checkDepartmentShortName = departmentRepository.findByShortName(department.getShortName());
        if (checkDepartmentShortName != null){
            errors.rejectValue("shortName",null , "ชื่อย่อ Department นี้มีผู้ใช้งานแล้ว");
        }
    }
}
