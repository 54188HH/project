package com.lzq.springbootmybatis01.Entity;

public class Admin {
    private Integer id;
    private String userName;
    private String password;
    public Admin(){}
    public Admin(String userName,String password){
        this.password=password;
        this.userName=userName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
