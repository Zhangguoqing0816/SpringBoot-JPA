package com.me.testjpa.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    private Status status;

    public Employee(String empNo, String empName, String empSex, String sal, Date makeTime) {
        this.empNo = empNo;
        this.empName = empName;
        this.empSex = empSex;
        this.sal = sal;
        this.makeTime = makeTime;
    }

    public Employee(String empNo, String empName, String empSex, String sal, Date makeTime, Status status) {
        this.empNo = empNo;
        this.empName = empName;
        this.empSex = empSex;
        this.sal = sal;
        this.makeTime = makeTime;
        this.status = status;
    }

    public Employee() {
    }

    public Employee(String empNo) {
        this.empNo = empNo;
    }

    public Employee(String empNo, String empName) {
        this.empNo = empNo;
        this.empName = empName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empNo, employee.empNo) &&
                Objects.equals(empName, employee.empName) &&
                Objects.equals(empSex, employee.empSex) &&
                Objects.equals(sal, employee.sal) &&
                Objects.equals(makeTime, employee.makeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), empNo, empName, empSex, sal, makeTime);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo='" + empNo + '\'' +
                ", empName='" + empName + '\'' +
                ", empSex='" + empSex + '\'' +
                ", sal='" + sal + '\'' +
                ", makeTime=" + makeTime +
                ", status=" + status +
                '}';
    }

    public Employee(String empNo, String empName, String sal) {
        this.empNo = empNo;
        this.empName = empName;
        this.sal = sal;
    }

    public Employee(String empNo, String empName, String sal, Status status) {
        this.empNo = empNo;
        this.empName = empName;
        this.sal = sal;
        this.status = status;
    }

    public enum  Status{
        FREE, //空闲的
        BUSY, //忙碌
        VOCATION; //休假
    }
}
