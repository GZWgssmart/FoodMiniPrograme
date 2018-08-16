package com.gzzy.foodManProgram.entity.common;

import java.util.Date;

/**
 * @ClassName App
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/14/014 19:10
 * @Version 1.0
 */
public class App {
    private int id;
    private String name;
    private String des;
    private Date createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
