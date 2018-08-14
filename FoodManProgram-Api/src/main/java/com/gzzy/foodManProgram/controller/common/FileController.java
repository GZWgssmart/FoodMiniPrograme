package com.gzzy.foodManProgram.controller.common;

import com.alibaba.fastjson.JSON;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.service.common.FileService;
import com.gzzy.foodManProgram.util.Util;
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

    @Value("${prop:upload-folder}")
    private String UPLOAD_FOLDER;
    private Logger logger = LoggerFactory.getLogger(FileController.class);


    @Autowired
    private FileService fileService;




   /* @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    @ApiOperation(value = "文件上传",notes = "适用场景：文件上传。上传成功将返回一个文件ID，可通过此文件ID对文件进行预览等操作",tags = {"店铺App","市场App"})
    public AbstractOutputDto upload(@ApiParam(name = "file", value = "文件集合") @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        AbstractOutputDto obj=new AbstractOutputDto();
        fileService.saveFile(file);
        obj.setStatus(0);
        obj.setMsg("上传成功");
        return obj;
    }*/




    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Object singleFileUpload(MultipartFile file) {
        logger.debug("传入的文件参数：{}", JSON.toJSONString(file, true));
        if (Objects.isNull(file) || file.isEmpty()) {
            logger.error("文件为空");
            return "文件为空，请重新上传";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            logger.debug("文件写入成功...");
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }

}
