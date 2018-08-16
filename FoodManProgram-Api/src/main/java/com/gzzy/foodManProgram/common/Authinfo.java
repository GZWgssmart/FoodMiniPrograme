package com.gzzy.foodManProgram.common;


public class Authinfo {
    private String accesstoken;//登陆认证信息

    private int status; //登录角色 1：门店 2：公司

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}