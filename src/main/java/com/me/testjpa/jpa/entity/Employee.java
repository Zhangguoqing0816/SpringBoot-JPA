package com.me.testjpa.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

// @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Data
@Table(name = "employee")
public class Employee extends BaseEntity {
    @Column(columnDefinition = "varchar(100) comment '员工编号'")
    private String empNo;
    @Column(columnDefinition = "varchar(100) comment '姓名'")
    private String empName;
    @Column(columnDefinition = "varchar(100) comment '性别'")
    private String empSex;
    @Column(columnDefinition = "varchar(100) comment '工资'")
    private String sal;
    @Column(columnDefinition = "datetime comment '操作时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date makeTime;

    public Employee(String empNo, String empName, String empSex, String sal, Date makeTime) {
        this.empNo = empNo;
        this.empName = empName;
        this.empSex = empSex;
        this.sal = sal;
        this.makeTime = makeTime;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo='" + empNo + '\'' +
                ", empName='" + empName + '\'' +
                ", empSex='" + empSex + '\'' +
                ", sal='" + sal + '\'' +
                ", makeTime=" + makeTime +
                '}';
    }

    public Employee(String empNo, String empName, String sal) {
        this.empNo = empNo;
        this.empName = empName;
        this.sal = sal;
    }
}
