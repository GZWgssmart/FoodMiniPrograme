package com.gzzy.foodManProgram.dto;

import java.util.Date;

/**
 * @ClassName StoreDto
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/22/022 17:36
 * @Version 1.0
 */
public class StoreDto {

    private String name;
    private String tel;
    private int appid;
    private String type;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
