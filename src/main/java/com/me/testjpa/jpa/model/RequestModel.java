package com.me.testjpa.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class RequestModel {

    private String rId;

    private String rNo;

    private String rName;

    private Date rTime;

    public RequestModel() {
    }

    public RequestModel(String rId, String rNo, String rName, Date rTime) {
        this.rId = rId;
        this.rNo = rNo;
        this.rName = rName;
        this.rTime = rTime;
    }
}
