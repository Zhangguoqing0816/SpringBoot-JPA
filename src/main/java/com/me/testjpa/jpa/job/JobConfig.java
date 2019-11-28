package com.me.testjpa.jpa.job;

import com.dangdang.ddframe.job.api.JobType;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.internal.operate.JobOperateAPIImpl;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

/**
 * @Description: 增加定时任务的工具类
 * @Author: ZhangGQ
 * @Date: 2019/9/3 10:37
 */
@Service
public class JobConfig {
    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    /**
     * @param //JobName 任务名称
     * @param cron 表达式
     * @param shardingCount 分片总数
     * @param //c 执行类
     * @param type 任务类型
    */
    public void initJob(SimpleJob simpleJob, String cron, int shardingCount, JobType type){
        System.out.println("JobConfig----start");
        createJob(simpleJob, cron, shardingCount, type);
        System.out.println("JobConfig----end");
    }

    /**
     * 增加定时任务方法
     * @param //JobName 任务名称
     * @param cron 表达式
     * @param shardingCount 分片总数
     * @param //c 执行类
     * @param type 任务类型
     * @return
     */
    public void createJob(SimpleJob simpleJob, String cron, int shardingCount, JobType type){
        //初始化配置
        JobScheduler jobScheduler = getJobScheduler1(simpleJob, cron, shardingCount, type);
        jobScheduler.init();
    }

    public void stopJob(SimpleJob simpleJob, String cron, int shardingCount, JobType type) {
/*
        JobOperateAPI jobOperateAPI = new JobOperateAPIImpl(zookeeperRegistryCenter);
        Optional<String> name = Optional.of(simpleJob.getClass().getCanonicalName());
        Optional<String> serverIP = Optional.of("127.0.0.1:2181");
        jobOperateAPI.shutdown(name, serverIP);*/

        //初始化配置
        JobScheduler jobScheduler = getJobScheduler1(simpleJob, cron, shardingCount, type);
        try {
            jobScheduler.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyJob(SimpleJob simpleJob, String cron, int shardingCount, JobType type){
        //初始化配置
        JobScheduler jobScheduler = getJobScheduler1(simpleJob, cron, shardingCount, type);
        jobScheduler.notify();
    }

    /**
     * 生成一个任务 Job
     */
    private JobScheduler getJobScheduler1(SimpleJob simpleJob, String cron, int shardingCount, JobType type) {
        System.out.println("jobName=" + simpleJob.getClass().getName());
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(simpleJob.getClass().getName(), cron, shardingCount).build();
        JobTypeConfiguration jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, simpleJob.getClass().getCanonicalName());
        if (type.compareTo(JobType.DATAFLOW) == 0) {
            jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration, simpleJob.getClass().getCanonicalName(), true);
        }
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(jobTypeConfiguration).overwrite(true).build();
        return new SpringJobScheduler(simpleJob, zookeeperRegistryCenter, liteJobConfiguration );
    }

    /*JobOperateAPI jobOperateAPI = new JobOperateAPIImpl();
        jobOperateAPI.shutdown();*/
}
