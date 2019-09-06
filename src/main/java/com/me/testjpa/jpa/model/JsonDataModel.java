package com.me.testjpa.jpa.model;

import lombok.Data;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/5 15:34
 */
@Data
public class JsonDataModel {

    private String fileName;

    private String taskId;

    private String sortNum;

    private String bg;

    private String ed;

    private String onebest;

    private String speaker;

    @Override
    public String toString() {
        return "JsonDataModel{" +
                "fileName='" + fileName + '\'' +
                ", taskId='" + taskId + '\'' +
                ", sortNum='" + sortNum + '\'' +
                ", bg='" + bg + '\'' +
                ", ed='" + ed + '\'' +
                ", onebest='" + onebest + '\'' +
                ", speaker='" + speaker + '\'' +
                '}';
    }
}
