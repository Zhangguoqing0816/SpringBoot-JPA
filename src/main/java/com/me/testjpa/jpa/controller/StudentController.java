package com.me.testjpa.jpa.controller;

import com.me.testjpa.jpa.entity.StudentEntity;
import com.me.testjpa.jpa.service.StudentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/10 15:38
 */
@RestController
@RequestMapping("/student")
public class StudentController{

    @Autowired
    private StudentEntityService studentEntityService;

    @GetMapping("/getStu/{str}")
    public ResponseEntity<List<StudentEntity>> queryStudent(@PathVariable String str){
        List<StudentEntity> query = studentEntityService.query(str);
        return ResponseEntity.ok().body(query);
    }

    @GetMapping("/getStu1/{str}")
    public ResponseEntity<List<StudentEntity>> queryStudent1(@PathVariable String str){
        List<StudentEntity> query = studentEntityService.queryAndBuilder(str);
        return ResponseEntity.ok().body(query);
    }
}
