package com.me.testjpa.jpa.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @Description: ElasticJob
 * @Author: ZhangGQ
 * @Date: 2019/9/3 10:23
 */
public class MyJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("定时任务名称：" + shardingContext.getJobName());
    }
}
