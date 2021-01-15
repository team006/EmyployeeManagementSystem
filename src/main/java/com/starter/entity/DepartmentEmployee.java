package com.starter.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "department_emp")
@Entity
public class DepartmentEmployee {

    @Id
    @GeneratedValue
    @Column(name = "department_emp_id")
    private Integer departmentEmpId;

    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "emp_id")
    private Integer empId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date from_date;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date to_date;

    public DepartmentEmployee() {
    }


}
