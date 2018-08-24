package com.gzzy.foodManProgram.service.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gzzy.foodManProgram.common.Config;

import com.gzzy.foodManProgram.common.ImgUrl;
import com.gzzy.foodManProgram.dto.ImgOutput;
import com.gzzy.foodManProgram.entity.common.Img;
import com.gzzy.foodManProgram.util.DataUtil;
import com.gzzy.foodManProgram.util.HttpClientUtil;
import com.gzzy.foodManProgram.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 87831 on 2018/2/6/0006.
 */
@Transactional
@Service
public class FileService {
    @Autowired
    private Config config;





    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Img saveFile(MultipartFile file) throws FileNotFoundException {
        File classpath = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!classpath.exists()) {
            classpath = new File("");
        }
        File upload = new File(classpath.getAbsolutePath(),"static/images/upload/");
        if(!upload.exists()) {
            upload.mkdirs();
        }
        String imgdirimage = upload.getAbsolutePath();
        // 文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //文件名称
        String realFileName = file.getOriginalFilename();
        String serialNo = Util.getStrDateForYYYYMMDDHHMMSS(new Date());
        serialNo = serialNo.substring(0, 8);
        String fileNewName = serialNo + UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
        String path = imgdirimage ;
        // 没有文件夹先创建
        File existfile = new File(path);
        if (!existfile.exists()) {// 判断文件是否存在
            existfile.mkdirs(); // 创建文件夹
        }
        String srcImgPath = path + "/" + fileNewName;

        Img img = new Img();
        File record = new File(srcImgPath);
        try {
            if(!record.exists()){
                record.createNewFile();
            }
            boolean flag =  HttpClientUtil.doUploadClientforfile(file.getBytes(), record);
            if(flag){//上传成功
                String url = "http://127.0.0.1:9009" + "/" + fileNewName;
                img.setImg(url);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }

}
