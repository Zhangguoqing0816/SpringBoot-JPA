package com.me.testjpa.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass //超类，不会映射出实体表
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})//不会序列化出括号内的属性，hibernate会自动生成这两个属性
public class BaseEntity implements Serializable {
    @Id //表中的主键、自增长形式
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(nullable = false, columnDefinition = "varchar(64) comment '主键'")
    private String id;
}
