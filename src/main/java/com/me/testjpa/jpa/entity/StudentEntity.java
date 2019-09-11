package com.me.testjpa.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/10 11:45
 */
@Table(name = "student_tab")
@org.hibernate.annotations.Table(appliesTo = "student_tab", comment = "学生表")
@Entity
@Data
public class StudentEntity{
    @Id
    @Column(columnDefinition = "VARCHAR(60) COMMENT '学生ID'")
    private String sId;
    @Column(columnDefinition = "VARCHAR(60) COMMENT '学生名称'")
    private String stuName;
    @Column(columnDefinition = "VARCHAR(60) COMMENT '学生性别'")
    private String stuSex;
    @Column(columnDefinition = "VARCHAR(60) COMMENT '课堂表ID'")
    private String claId;
}
