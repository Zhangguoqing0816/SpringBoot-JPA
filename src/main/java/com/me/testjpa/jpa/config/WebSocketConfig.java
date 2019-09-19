package com.me.testjpa.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: ZhangGQ
 * @Date: 2019/09/17
 * Description: 启用 Web 的支持
 */
@Configuration
public class WebSocketConfig {

    /**
     * 开启 Web 支持
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
