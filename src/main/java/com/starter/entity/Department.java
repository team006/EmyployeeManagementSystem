package com.starter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "department")
@Getter
@Setter
@Entity
public class Department {

    @Id
    @Column(name = "department_id")
    private Integer departmentId;

    private String name;

    @Column(name = "short_name")
    private String shortName;


    public Department() {
    }

}
