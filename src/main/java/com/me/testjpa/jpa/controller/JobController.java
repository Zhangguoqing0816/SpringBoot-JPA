package com.me.testjpa.jpa.controller;

import com.dangdang.ddframe.job.api.JobType;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.me.testjpa.jpa.config.ApplicationContextUtil;
import com.me.testjpa.jpa.job.JobConfig;
import com.me.testjpa.jpa.job.MyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/3 14:36
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobConfig jobConfig;
    @Autowired
    private MyJob myJob;

    @RequestMapping("/createJob")
    public String createJob(){
//        SimpleJob myJob = ApplicationContextUtil.getBean("myJob", SimpleJob.class);
        jobConfig.initJob(myJob,"0/5 * * * * ?",1,  JobType.SIMPLE);
        return "ok";
    }

    @RequestMapping("/stopJob")
    public String stopJob() throws InterruptedException {
        jobConfig.stopJob(myJob,"0/5 * * * * ?",1,  JobType.SIMPLE);
        return "ok";
    }

    @RequestMapping("/notifyJob")
    public String notifyJob() throws InterruptedException {
        jobConfig.notifyJob(myJob,"0/5 * * * * ?",1,  JobType.SIMPLE);
        return "ok";
    }
}
