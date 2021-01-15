package com.starter.repository;

import org.springframework.data.repository.CrudRepository;

import com.starter.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRepository extends CrudRepository<Employee,Integer> {
    List<Employee> findEmployeeByDepartment(String department);
}
