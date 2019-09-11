package com.me.testjpa.jpa.service;

import com.me.testjpa.jpa.repository.ClassEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/10 14:23
 */
@Service("classEntityService")
public class ClassEntityService {
    @Autowired
    private ClassEntityRepository classEntityRepository;


}
