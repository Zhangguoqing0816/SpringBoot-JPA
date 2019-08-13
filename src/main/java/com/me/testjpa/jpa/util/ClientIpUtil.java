package com.me.testjpa.jpa.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class ClientIpUtil {

    public static String ClientIp(HttpServletRequest request) {
        //X-Forwarded-For，不区分大小写
        String possibleIpStr = request.getHeader("X-Forwarded-For");
        String remoteIp = request.getRemoteAddr();
        String clientIp;
        if (StringUtils.isNotBlank(possibleIpStr) && !"unknown".equalsIgnoreCase(possibleIpStr)) {
            //可能经过好几个转发流程，第一个是用户的真实ip，后面的是转发服务器的ip
            clientIp = possibleIpStr.split(",")[0].trim();
        } else {
            //如果转发头ip为空，说明是直接访问的，没有经过转发
            clientIp = remoteIp;
        }
        return clientIp;
    }
}
