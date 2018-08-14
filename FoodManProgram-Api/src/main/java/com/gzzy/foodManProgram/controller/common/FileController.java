package com.gzzy.foodManProgram.controller.common;

import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.util.Util;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by s7 on 2018/2/5.
 */


@RestController
@RequestMapping("/files")
public class FileController  {


    /**
     * 上传成功后返回上传的文件信息
     *
     * @param file
     * @param request
     * @return
     */

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    @ApiOperation(value = "文件上传",notes = "适用场景：文件上传。上传成功将返回一个文件ID，可通过此文件ID对文件进行预览等操作",tags = {"店铺App","市场App"})
    public AbstractOutputDto upload(@ApiParam(name = "file", value = "文件集合") @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        AbstractOutputDto obj=new AbstractOutputDto();
        int aemtype = Util.convertToInt(request.getParameter("aemtype"));
       /* ImgOutput imgOutput = fileService.saveFile(file,aemtype );*/
        obj.setStatus(0);
        obj.setMsg("上传成功");
        return obj;
    }


}
