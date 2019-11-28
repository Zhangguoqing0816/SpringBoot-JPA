package com.me.testjpa.jpa.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.me.testjpa.jpa.entity.Employee;
import com.me.testjpa.jpa.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: ElasticJob
 * @Author: ZhangGQ
 * @Date: 2019/9/3 10:23
 */
@Component
public class MyJob implements SimpleJob {

    @Autowired
    private EmpService empService;

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("定时任务名称：" + shardingContext.getJobName());
        List<Employee> employees = empService.selectAll();
        System.out.println(employees.toString());
    }
}
