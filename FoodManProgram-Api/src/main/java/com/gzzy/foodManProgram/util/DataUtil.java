package com.gzzy.foodManProgram.util;


public class DataUtil {

    private int apcid; //门店id
    private String apcname; //门店名称;

    private int apaccountid; //公司id
    private String apaccountname; //公司名称

    public int getApcid() {
        return apcid;
    }

    public void setApcid(int apcid) {
        this.apcid = apcid;
    }

    public String getApcname() {
        return apcname;
    }

    public void setApcname(String apcname) {
        this.apcname = apcname;
    }

    public int getApaccountid() {
        return apaccountid;
    }

    public void setApaccountid(int apaccountid) {
        this.apaccountid = apaccountid;
    }

    public String getApaccountname() {
        return apaccountname;
    }

    public void setApaccountname(String apaccountname) {
        this.apaccountname = apaccountname;
    }

    private boolean customer;

    private boolean staff;


    private boolean technician;


    private static final ThreadLocal LOCAL = new ThreadLocal() {
        protected synchronized Object initialValue() {
          return new DataUtil();
        }
    };
    
    private DataUtil() {
    }
    
    public static DataUtil getCurrent() {
        return (DataUtil) LOCAL.get();
    }



    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public boolean isTechnician() {
        return technician;
    }

    public void setTechnician(boolean technician) {
        this.technician = technician;
    }



    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }



}
