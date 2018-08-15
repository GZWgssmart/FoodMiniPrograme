package com.gzzy.foodManProgram.service.common;

import com.gzzy.foodManProgram.dao.common.ProductDao;
import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.entity.common.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/15/015 13:03
 * @Version 1.0
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public AbstractOutputDto queryProductAll(AbstractInputDto<Product> parmeter) {
        AbstractOutputDto abstractOutputDto=new AbstractOutputDto();

        int startnum=(parmeter.getPage()-1)*parmeter.getRows();
        int endnum=parmeter.getPage()*parmeter.getRows();

        int totalrecords=productDao.queryProductAllCount(parmeter.getData());

        int totalpages=(totalrecords + parmeter.getRows()-1)/parmeter.getRows();
        abstractOutputDto.setCurrpage(parmeter.getPage());
        abstractOutputDto.setStatus(0);
        abstractOutputDto.setMsg("请求成功");
        abstractOutputDto.setTotalpages(totalpages);
        abstractOutputDto.setTotalrecords(totalrecords);
        List<Product> products=productDao.queryProductAll(parmeter.getSidx(),parmeter.getSord(),startnum,endnum,parmeter.getData());
        abstractOutputDto.setDatalist(products);
        return abstractOutputDto;
    }


    public void insertProduct(Product product) {
        productDao.insertProduct(product);
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
}
