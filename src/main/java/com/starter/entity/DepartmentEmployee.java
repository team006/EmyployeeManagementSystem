package com.starter.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "department_emp")
@Entity
public class DepartmentEmployee {

    @Id
    @GeneratedValue
    private Integer department_emp_id;
    private Integer department_id;
    private Integer emp_id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date from_date;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date to_date;

    public DepartmentEmployee() {
    }


}
