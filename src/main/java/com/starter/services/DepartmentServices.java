package com.starter.services;

import com.starter.entity.Department;

import java.util.List;

public interface DepartmentServices {
    Iterable<Department> listDepartment();
    Department addDepartment(Department department);
}
