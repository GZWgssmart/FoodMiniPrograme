package com.gzzy.foodManProgram.controller.common;

import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.entity.common.Store;
import com.gzzy.foodManProgram.service.common.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StoreController
 * @Author wangbin
 * @Date 2018/8/14/014 19:44
 * @Version 1.0
 */

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/queryAppAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询商家",notes = "查询商家")
    public AbstractOutputDto queryUser(@ApiParam(name="查询商家",value = "传入JSON格式") @RequestBody AbstractInputDto<Store> parmeter){
        AbstractOutputDto obj = new AbstractOutputDto();
        obj = storeService.queryStoreAll(parmeter);
        return obj;
    }

    @RequestMapping(value = "/addApp",method = RequestMethod.POST)
    @ApiOperation(value = "添加商家",notes = "添加商家")
    public AbstractOutputDto addApp(@ApiParam(name="添加商家",value = "传入JSON格式") @RequestBody Store store){
        AbstractOutputDto obj = new AbstractOutputDto();
        storeService.insertApp(store);
        obj.setStatus(0);
        obj.setMsg("添加成功");
        return obj;
    }

    @RequestMapping(value = "/updateApp",method = RequestMethod.POST)
    @ApiOperation(value = "修改商家",notes = "修改商家")
    public AbstractOutputDto updateApp(@ApiParam(name="修改商家",value = "传入JSON格式") @RequestBody Store store){
        AbstractOutputDto obj = new AbstractOutputDto();
        storeService.updateApp(store);
        obj.setStatus(0);
        obj.setMsg("修改成功");
        return obj;
    }
}
