package com.lzq.springbootmybatis01.entity;

import java.io.Serializable;

public class OrderRecord implements Serializable {
    private Integer id;
    private String userId;

    public OrderRecord(Integer id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
