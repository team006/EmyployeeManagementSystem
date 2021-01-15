package com.starter.repository;

import com.starter.entity.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Integer> {
    List<DepartmentEmployee> findByDepartmentId(Integer departmentId);
    DepartmentEmployee findByEmpId(Integer empId);
}
