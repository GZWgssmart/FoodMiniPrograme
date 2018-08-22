package com.gzzy.foodManProgram.controller.common;

import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.dto.StoreDto;
import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.entity.common.Store;
import com.gzzy.foodManProgram.entity.common.bean.ValueLabel;
import com.gzzy.foodManProgram.service.common.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/queryStoreAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询商家",notes = "查询商家")
    public AbstractOutputDto queryUser(@ApiParam(name="查询商家",value = "传入JSON格式") @RequestBody AbstractInputDto<StoreDto> parmeter){
        AbstractOutputDto obj = new AbstractOutputDto();
        obj = storeService.queryStoreAll(parmeter);
        return obj;
    }

    @RequestMapping(value = "/addStore",method = RequestMethod.POST)
    @ApiOperation(value = "添加商家",notes = "添加商家")
    public AbstractOutputDto addApp(@ApiParam(name="添加商家",value = "传入JSON格式") @RequestBody Store store){
        AbstractOutputDto obj = new AbstractOutputDto();
        storeService.insertStore(store);
        obj.setStatus(0);
        obj.setMsg("添加成功");
        return obj;
    }

    @RequestMapping(value = "/updateStore",method = RequestMethod.POST)
    @ApiOperation(value = "修改商家",notes = "修改商家")
    public AbstractOutputDto updateApp(@ApiParam(name="修改商家",value = "传入JSON格式") @RequestBody Store store){
        AbstractOutputDto obj = new AbstractOutputDto();
        storeService.updateStore(store);
        obj.setStatus(0);
        obj.setMsg("修改成功");
        return obj;
    }

    @RequestMapping(value = "/queryStroeProductByAppid",method = RequestMethod.POST)
    @ApiOperation(value = "根据appid查询商铺和商品",notes = "根据appid查询商铺和商品")
    public AbstractOutputDto queryStroeProductByAppid(@ApiParam(name="根据appid查询商铺和商品",value = "传入JSON格式") @RequestBody App app){
        AbstractOutputDto obj = new AbstractOutputDto();
        obj = storeService.queryStroeProductByAppid(app);
        obj.setStatus(0);
        return obj;
    }

    @RequestMapping(value = "/queryStroeProductByid",method = RequestMethod.POST)
    @ApiOperation(value = "根据商铺id查询商品",notes = "根据商铺id查询商品")
    public AbstractOutputDto queryStroeProductByid(@ApiParam(name="根据商铺id查询商品",value = "传入JSON格式") @RequestBody Store store){
        AbstractOutputDto obj = new AbstractOutputDto();
        obj = storeService.queryStroeProductByid(store);
        obj.setStatus(0);
        return obj;
    }

    @RequestMapping(value = "/enableddisable",method = RequestMethod.POST)
    @ApiOperation(value = "启用禁用商铺",notes = "启用禁用商铺")
    public AbstractOutputDto enableddisable(@ApiParam(name="启用禁用商铺",value = "传入JSON格式") @RequestBody Store store){
        AbstractOutputDto obj = new AbstractOutputDto();
        storeService.enableddisable(store);
        obj.setStatus(0);
        return obj;
    }

    @RequestMapping(value = "/queryStoreValueLabel",method = RequestMethod.POST)
    @ApiOperation(value = "查询所有商家",notes = "查询所有商家")
    public AbstractOutputDto queryAppValueLabel(){
        AbstractOutputDto obj = new AbstractOutputDto();
        List<ValueLabel> valueLabels = storeService.queryStoreValueLabel();
        obj.setDatalist(valueLabels);
        return obj;
    }
}
