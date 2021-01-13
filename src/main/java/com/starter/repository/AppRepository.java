package com.starter.repository;

import org.springframework.data.repository.CrudRepository;

import com.starter.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends CrudRepository<Employee,Integer> {

}
