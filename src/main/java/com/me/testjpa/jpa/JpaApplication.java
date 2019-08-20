package com.me.testjpa.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableCaching
public class JpaApplication {
    private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);

    //One
    //zhangBranchOne
    // This is master
    public static void main(String[] args) throws UnknownHostException {
        //SpringApplication.run(JpaApplication.class, args);
        SpringApplication app = new SpringApplication(JpaApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info("\n-----------------------------------------------------------------\n" +
                "       Application '{}' is running!  \n" +
                "       Access URLS: \n" +
                "           localhost:  http://localhost:{} \n" +
                "           localIp:    http://{}:{} \n" +
                "           SwaggerUI:  http://localhost:{}/swagger-ui.html \n" +
                "-----------------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
                env.getProperty("server.port")
                );
    }

/**
    # 根据过滤器配置字符编码 false 是将原来机制失效 过滤器配置的才生效#
    spring.http.encoding.enabled=false
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("utf-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
 */


}
