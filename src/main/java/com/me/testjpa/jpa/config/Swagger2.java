package com.me.testjpa.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描包
                .apis(RequestHandlerSelectors.basePackage("com.me.testjpa.jpa.controller"))
                .paths(PathSelectors.any())
                .build();
                //.securitySchemes();
    }

    /**
     * @Description: 构建 api文档的信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                .title(" 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("作者：张国庆", null, null))
                //版本号
                .version("第一版_1.0")
                //描述
                .description("Swagger2 API描述").build();

    }
}
