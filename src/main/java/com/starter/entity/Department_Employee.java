package com.starter.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "department_emp")
public class Department_Employee {

    @Id
    private Integer department_emp_id;

    @OneToOne
    private Department department_id;

    @OneToMany
    private Employee employee_id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date from_date;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date to_date;

    public Department_Employee() {
    }


}
