package com.gzzy.foodManProgram.service.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gzzy.foodManProgram.common.Config;

import com.gzzy.foodManProgram.common.ImgUrl;
import com.gzzy.foodManProgram.dto.ImgOutput;
import com.gzzy.foodManProgram.util.HttpClientUtil;
import com.gzzy.foodManProgram.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;

/**
 * Created by 87831 on 2018/2/6/0006.
 */
@Transactional
@Service
public class FileService {
    @Autowired
    private Config config;





    private Logger logger = LoggerFactory.getLogger(this.getClass());

   public ImgOutput saveFile(MultipartFile file, int aemtype) {
       // 文件后缀
       String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
       //文件名称
       String realFileName = file.getOriginalFilename();
       String serialNo = Util.getStrDateForYYYYMMDDHHMMSS(new Date());
       serialNo = serialNo.substring(0,8);
       String fileNewName = realFileName+"."+suffix;
       String imgdirimagepath = ImgUrl.imgdirimage;
       String imgdirvideopath = ImgUrl.imgdirvideo;
       // 没有文件夹先创建
       File existfile1 = new File(imgdirimagepath);
       if (!existfile1.exists()) {// 判断文件是否存在
           existfile1.mkdirs(); // 创建文件夹
       }
       // 没有文件夹先创建
       File existfile2 = new File(imgdirvideopath);
       if (!existfile2.exists()) {// 判断文件是否存在
           existfile2.mkdirs(); // 创建文件夹
       }


       String srcImgPath = "";
       String imgurl = "";

       File record = new File(srcImgPath);
       int picid = 0;
       ImgOutput imgOutput = new ImgOutput();
       try {
           if(!record.exists()){
               record.createNewFile();
           }
           boolean flag =  HttpClientUtil.doUploadClientforfile(file.getBytes(), record);
           if(flag){//上传成功

           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return imgOutput;
   }

}
