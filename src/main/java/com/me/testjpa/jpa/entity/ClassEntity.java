package com.me.testjpa.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/10 11:46
 */
@Table(name = "class_tab")
@Entity
@Data
public class ClassEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(60) COMMENT '课堂表ID'")
    private String claId;
    @Column(columnDefinition = "VARCHAR(60) COMMENT '课堂表名称'")
    private String claName;
    @Column(columnDefinition = "VARCHAR(60) COMMENT '课堂表描述'")
    private String claContent;
}
