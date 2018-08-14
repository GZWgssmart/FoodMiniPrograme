package com.gzzy.foodManProgram.dto;

import java.util.List;

public class AbstractOutputDto <T> {
    String msg;
    int status; // -1 无数据
    T data;
    List<T> datalist;
    List<T> detaillist;

    int currpage = 0;	//当前页数
    int totalrecords = 0;	//全件数
    int totalpages = 0;  //全页数
    //排序列
    String sidx;

    //排序规则
    String sord;

    public List<T> getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(List<T> detaillist) {
        this.detaillist = detaillist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }

    public int getCurrpage() {
        return currpage;
    }

    public void setCurrpage(int currpage) {
        this.currpage = currpage;
    }

    public int getTotalrecords() {
        return totalrecords;
    }

    public void setTotalrecords(int totalrecords) {
        this.totalrecords = totalrecords;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public static AbstractOutputDto error(String msg){
        AbstractOutputDto<Object> outputDto = new AbstractOutputDto<>();
        outputDto.setStatus(0);
        outputDto.setMsg(msg);
        return outputDto;
    }
}
