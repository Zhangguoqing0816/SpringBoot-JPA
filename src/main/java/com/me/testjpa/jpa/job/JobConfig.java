package com.me.testjpa.jpa.job;

import com.dangdang.ddframe.job.api.JobType;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description: 增加定时任务的工具类
 * @Author: ZhangGQ
 * @Date: 2019/9/3 10:37
 */
@Configuration
/*@Component
@RequiredArgsConstructor*/
public class JobConfig /*implements CommandLineRunner*/ {
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
        /*addJob(createJob("myjob1","0/5 * * * * ?",2, MyJob.class, JobType.SIMPLE));
        addJob(createJob("myjob2","0/5 * * * * ?",2, MyJob.class, JobType.SIMPLE));*/

        System.out.println("JobConfig----start");
        LiteJobConfiguration job = createJob(JobName, cron, shardingCount, c, type);
        addJob(job);
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
    public LiteJobConfiguration createJob(String JobName, String cron, int shardingCount, Class c, JobType type){
        //初始化配置
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(JobName, cron, shardingCount).build();
        JobTypeConfiguration jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, c.getCanonicalName());
        if(type.compareTo(JobType.DATAFLOW) == 0){
            jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration, c.getCanonicalName(), true);
        }
        return LiteJobConfiguration.newBuilder(jobTypeConfiguration).build();
    }

    public void addJob(LiteJobConfiguration liteJobConfiguration){
        JobScheduler jobScheduler = new JobScheduler(zookeeperRegistryCenter, liteJobConfiguration);
        jobScheduler.init();
    }

    /*@Override
    public void run(String... args) throws Exception {
        System.out.println("任务初始化。。。");
        initJob();
    }*/
}
