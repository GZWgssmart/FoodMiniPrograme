package com.gzzy.foodManProgram.controller.common;

import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.dto.ValueLableDto;
import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.bean.ValueLabel;
import com.gzzy.foodManProgram.service.common.AppService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AppController
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/14/014 19:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/queryAppAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询小程序",notes = "查询小程序")
    public AbstractOutputDto queryUser(@ApiParam(name="查询小程序",value = "传入JSON格式") @RequestBody AbstractInputDto<App> parmeter){
        AbstractOutputDto obj = new AbstractOutputDto();
        obj = appService.queryAppAll(parmeter);
        return obj;
    }

    @RequestMapping(value = "/addApp",method = RequestMethod.POST)
    @ApiOperation(value = "添加小程序",notes = "添加小程序")
    public AbstractOutputDto addApp(@ApiParam(name="添加小程序",value = "传入JSON格式") @RequestBody App app){
        AbstractOutputDto obj = new AbstractOutputDto();
        appService.insertApp(app);
        obj.setStatus(0);
        obj.setMsg("添加成功");
        return obj;
    }

    @RequestMapping(value = "/updateApp",method = RequestMethod.POST)
    @ApiOperation(value = "修改小程序",notes = "修改小程序")
    public AbstractOutputDto updateApp(@ApiParam(name="修改小程序",value = "传入JSON格式") @RequestBody App app){
        AbstractOutputDto obj = new AbstractOutputDto();
        appService.updateApp(app);
        obj.setStatus(0);
        obj.setMsg("修改成功");
        return obj;
    }

    @RequestMapping(value = "/queryAppValueLabel",method = RequestMethod.POST)
    @ApiOperation(value = "查询所有小程序",notes = "查询所有小程序")
    public AbstractOutputDto queryAppValueLabel(@ApiParam(name="查询所有小程序",value = "传入JSON格式") @RequestBody ValueLableDto valueLableDto){
        AbstractOutputDto obj = new AbstractOutputDto();
        List<ValueLabel> valueLabels = appService.queryAppValueLabel(valueLableDto);
        obj.setDatalist(valueLabels);
        return obj;
    }
}
