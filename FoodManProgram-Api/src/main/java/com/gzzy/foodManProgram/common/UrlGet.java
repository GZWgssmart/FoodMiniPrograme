package com.gzzy.foodManProgram.common;

import java.text.DecimalFormat;

public class UrlGet {

    public static String licenseuploadurl = "http://115.29.10.68:8080/zscarinfo/car/drivinglicenseformultipartFile";

    public static String erpdataurl = "http://120.26.67.161:8687/erpdata/";



    public static String getimgurl(int picid,int aemtype){
        String url = "";
        if (aemtype == 1) {
            url  = "http://47.100.86.157:9010/image/" + picid + ".jpg";
        } else if (aemtype == 2) {
             url = "http://47.100.86.157:9010/video/" + picid + ".mp3";
        } else {
             url = "http://47.100.86.157:9010/fiv/" + picid + ".mp4";
        }
        return url;
    }

}
