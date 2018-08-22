package com.gzzy.foodManProgram.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ProductDto
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/22/022 17:35
 * @Version 1.0
 */
public class ProductDto {

    private int sid;
    private String name;
    private String title;
    private BigDecimal pricestart;
    private BigDecimal priceend;
    private int appid;
    private String status;


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

    public BigDecimal getPricestart() {
        return pricestart;
    }

    public void setPricestart(BigDecimal pricestart) {
        this.pricestart = pricestart;
    }

    public BigDecimal getPriceend() {
        return priceend;
    }

    public void setPriceend(BigDecimal priceend) {
        this.priceend = priceend;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
