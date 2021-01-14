package com.starter.services;

import com.starter.entity.Department;

import java.util.List;

public interface DepartmentServices {
    List<Department> listDepartment();
    Department addDepartment(Department department);
    Department getDepartmentById(Integer departmentId);
}
