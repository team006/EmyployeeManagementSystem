package com.starter.services;

import com.starter.entity.DepartmentEmployee;
import com.starter.entity.Employee;

import java.util.List;

public interface DepartmentEmployeeSerivces {
    List<Employee> findEmpolyeeByDepartmentId(Integer departmentId);
}
