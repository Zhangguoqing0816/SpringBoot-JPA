package com.me.testjpa.jpa.repository;

import com.me.testjpa.jpa.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/10 14:16
 */
public interface StudentEntityRepository extends JpaRepository<StudentEntity, String>, QuerydslPredicateExecutor<StudentEntity> {
    //
}
