package com.gzzy.foodManProgram.controller.common;

import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.service.common.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductContrller
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/15/015 13:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductContrller {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/queryProductAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询商品",notes = "查询商品")
    public AbstractOutputDto queryUser(@ApiParam(name="查询商品",value = "传入JSON格式") @RequestBody AbstractInputDto<Product> parmeter){
        AbstractOutputDto obj = new AbstractOutputDto();
        obj = productService.queryProductAll(parmeter);
        return obj;
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    @ApiOperation(value = "添加商品",notes = "添加商品")
    public AbstractOutputDto addApp(@ApiParam(name="添加商品",value = "传入JSON格式") @RequestBody Product product){
        AbstractOutputDto obj = new AbstractOutputDto();
        productService.insertProduct(product);
        obj.setStatus(0);
        obj.setMsg("添加成功");
        return obj;
    }

    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    @ApiOperation(value = "修改商品",notes = "修改商品")
    public AbstractOutputDto updateApp(@ApiParam(name="修改商品",value = "传入JSON格式") @RequestBody Product product){
        AbstractOutputDto obj = new AbstractOutputDto();
        productService.updateProduct(product);
        obj.setStatus(0);
        obj.setMsg("修改成功");
        return obj;
    }

    @RequestMapping(value = "/enableddisable",method = RequestMethod.POST)
    @ApiOperation(value = "启用禁用商品",notes = "启用禁用修改商品")
    public AbstractOutputDto enableddisable(@ApiParam(name="启用禁用修改商品",value = "传入JSON格式") @RequestBody Product product){
        AbstractOutputDto obj = new AbstractOutputDto();
        productService.enableddisable(product);
        obj.setStatus(0);
        return obj;
    }
}
