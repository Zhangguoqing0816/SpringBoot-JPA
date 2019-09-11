package com.me.testjpa.jpa.repository;

import com.me.testjpa.jpa.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/10 14:17
 */
public interface ClassEntityRepository extends JpaRepository<ClassEntity, String>, QuerydslPredicateExecutor<ClassEntity> {
}
