package com.starter.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "department")
@Entity
public class Department {

    @Id
    @Column(name = "department_id")
    private Integer department_id;

    private String name;
    private String short_name;


    public Department() {
    }


}
