package com.gzzy.foodManProgram.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="studyarea")
public class Config {
    String loginurl;
    private String vinurl;


    public String getVinurl() {
        return vinurl;
    }

    public void setVinurl(String vinurl) {
        this.vinurl = vinurl;
    }

    public String getLoginurl() {
        return loginurl;
    }

    public void setLoginurl(String loginurl) {
        this.loginurl = loginurl;
    }
}