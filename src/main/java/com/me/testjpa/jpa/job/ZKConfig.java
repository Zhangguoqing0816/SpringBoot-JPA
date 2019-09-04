package com.me.testjpa.jpa.job;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/3 10:33
 */
@Configuration
public class ZKConfig {

    @Value("${elaticjob.zookeeper.server-lists:#{null}}")
    private String zkHost ;
    @Value("${elaticjob.zookeeper.namespace:#{null} }")
    private String namespace;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter(){
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(zkHost, namespace);
        //base-sleep-time-milliseconds="1000"  等待重试的时间毫秒数（初始值）
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(1000);
        //max-sleep-time-milliseconds="3000"   等待重试的最大时间毫秒数
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(3000);
        //最大重试次数
        zookeeperConfiguration.setMaxRetries(3);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

}
