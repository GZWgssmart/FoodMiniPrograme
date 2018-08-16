package com.gzzy.foodManProgram.dto;

public class LoginDto {
    private String username;        //登录用户名
    private String userpsd;         //登录密码

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpsd() {
        return userpsd;
    }

    public void setUserpsd(String userpsd) {
        this.userpsd = userpsd;
    }
}
