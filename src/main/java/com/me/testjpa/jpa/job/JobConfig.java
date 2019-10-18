package com.me.testjpa.jpa.job;

import com.dangdang.ddframe.job.api.JobType;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param JobName 任务名称
     * @param cron 表达式
     * @param shardingCount 分片总数
     * @param c 执行类
     * @param type 任务类型
     */
    public void initJob(String JobName, String cron, int shardingCount, Class c, JobType type){
        System.out.println("JobConfig----start");
        createJob(JobName, cron, shardingCount, c, type);
        System.out.println("JobConfig----end");
    }

    /**
     * 增加定时任务方法
     * @param JobName 任务名称
     * @param cron 表达式
     * @param shardingCount 分片总数
     * @param c 执行类
     * @param type 任务类型
     * @return
     */
    public void createJob(String JobName, String cron, int shardingCount, Class c, JobType type){
        //初始化配置
        JobScheduler jobScheduler = getJobScheduler(JobName, cron, shardingCount, c, type);
        jobScheduler.init();
    }

    public void stopJob(String JobName, String cron, int shardingCount, Class c, JobType type) {
        //初始化配置
        JobScheduler jobScheduler = getJobScheduler(JobName, cron, shardingCount, c, type);
        try {
            jobScheduler.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyJob(String JobName, String cron, int shardingCount, Class c, JobType type){
        //初始化配置
        JobScheduler jobScheduler = getJobScheduler(JobName, cron, shardingCount, c, type);
        jobScheduler.notify();
    }

    /**
     * 生成一个任务 Job
     * @param JobName
     * @param cron
     * @param shardingCount
     * @param c
     * @param type
     * @return
     */
    private JobScheduler getJobScheduler(String JobName, String cron, int shardingCount, Class c, JobType type) {
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(JobName, cron, shardingCount).build();
        JobTypeConfiguration jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, c.getCanonicalName());
        if (type.compareTo(JobType.DATAFLOW) == 0) {
            jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration, c.getCanonicalName(), true);
        }
        LiteJobConfiguration build = LiteJobConfiguration.newBuilder(jobTypeConfiguration).build();
        return new JobScheduler(zookeeperRegistryCenter, build);
    }


}
