package com.gzzy.foodManProgram.controller.common;

import com.alibaba.fastjson.JSON;
import com.gzzy.foodManProgram.common.ImgUrl;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.entity.common.Img;
import com.gzzy.foodManProgram.service.common.FileService;
import com.gzzy.foodManProgram.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by s7 on 2018/2/5.
 */


@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileController  {

    @Autowired
    private FileService fileService;


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ApiOperation(value = "上传图片视频",notes = "上传图片视频")
    public AbstractOutputDto singleFileUpload(MultipartFile file) {
        AbstractOutputDto obj=new AbstractOutputDto();
        Img img = fileService.saveFile(file);
        obj.setData(img);
        obj.setStatus(0);
        obj.setMsg("上传成功");
        return obj;
    }

}
