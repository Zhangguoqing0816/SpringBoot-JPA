package com.me.testjpa.jpa.service;

import com.me.testjpa.jpa.entity.QClassEntity;
import com.me.testjpa.jpa.entity.QStudentEntity;
import com.me.testjpa.jpa.entity.StudentEntity;
import com.me.testjpa.jpa.repository.StudentEntityRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/10 14:18
 */
@Service
public class StudentEntityService {
    @Autowired
    private StudentEntityRepository studentEntityRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory jpaQueryFactory;

    @PostConstruct
    public void init(){
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    public List<StudentEntity> query(String str){
        QStudentEntity qStudentEntity = QStudentEntity.studentEntity;
        QClassEntity qClassEntity = QClassEntity.classEntity;
        List<StudentEntity> fetch = jpaQueryFactory.select(qStudentEntity)
                .from(qClassEntity, qStudentEntity)
                .where(
                        qStudentEntity.claId.eq(qClassEntity.claId)
                                .and(qClassEntity.claName.eq(str))
                ).fetch();
        return fetch;
    }

    public List<StudentEntity> queryAndBuilder(String str){
        QStudentEntity qStudentEntity = QStudentEntity.studentEntity;
        QClassEntity qClassEntity = QClassEntity.classEntity;
        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotBlank(str)){
            builder.and(qStudentEntity.claId.eq(qClassEntity.claId));
            builder.and(qClassEntity.claName.eq(str));
        }
        List<StudentEntity> fetch = jpaQueryFactory.select(qStudentEntity)
                .from(qClassEntity, qStudentEntity)
                .where(builder)
                .fetch();
        return fetch;
    }

}
