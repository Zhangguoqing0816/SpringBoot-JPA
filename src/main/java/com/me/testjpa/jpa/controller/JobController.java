package com.me.testjpa.jpa.controller;

import com.dangdang.ddframe.job.api.JobType;
import com.me.testjpa.jpa.job.JobConfig;
import com.me.testjpa.jpa.job.MyJob;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/createJob")
    public String createJob(){
        jobConfig.initJob("myjob1","0/5 * * * * ?",2, MyJob.class, JobType.SIMPLE);
//        jobConfig.initJob("myjob2","0 22 15 3 9 ? 2019",2, MyJob.class, JobType.SIMPLE);
        return "ok";
    }

    @RequestMapping("/stopJob")
    public String stopJob() throws InterruptedException {
        jobConfig.stopJob("myjob1","0/5 * * * * ?",2, MyJob.class, JobType.SIMPLE);
        return "ok";
    }

    @RequestMapping("/notifyJob")
    public String notifyJob() throws InterruptedException {
        jobConfig.notifyJob("myjob1","0/5 * * * * ?",2, MyJob.class, JobType.SIMPLE);
        return "ok";
    }
}
