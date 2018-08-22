package com.gzzy.foodManProgram.entity.common;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Product
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/14/014 19:11
 * @Version 1.0
 */
public class Product {
    private int id;
    private int sid;
    private String name;
    private String title;
    private BigDecimal price;
    private String des;
    private String img;
    private int appid;
    private Date createtime;
    private String status;
    private String imgdetail; //长图

    private String appname;
    private String storename;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getImgdetail() {
        return imgdetail;
    }

    public void setImgdetail(String imgdetail) {
        this.imgdetail = imgdetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
