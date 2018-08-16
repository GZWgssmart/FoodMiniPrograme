package com.gzzy.foodManProgram.service.common;

import com.gzzy.foodManProgram.dao.common.StoreDao;
import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.entity.common.Store;
import com.gzzy.foodManProgram.entity.common.StoreProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StoreService
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/15/015 12:32
 * @Version 1.0
 */
@Service
public class StoreService {

    @Autowired
    private StoreDao storeDao;

    public AbstractOutputDto queryStoreAll(AbstractInputDto<Store> parmeter) {
        AbstractOutputDto abstractOutputDto=new AbstractOutputDto();

        int startnum=(parmeter.getPage()-1)*parmeter.getRows();
        int endnum=parmeter.getPage()*parmeter.getRows();

        int totalrecords=storeDao.queryStoreAllCount(parmeter.getData());

        int totalpages=(totalrecords + parmeter.getRows()-1)/parmeter.getRows();
        abstractOutputDto.setCurrpage(parmeter.getPage());
        abstractOutputDto.setStatus(0);
        abstractOutputDto.setMsg("请求成功");
        abstractOutputDto.setTotalpages(totalpages);
        abstractOutputDto.setTotalrecords(totalrecords);
        List<Store> stores=storeDao.queryStoreAll(parmeter.getSidx(),parmeter.getSord(),startnum,endnum,parmeter.getData());
        abstractOutputDto.setDatalist(stores);
        return abstractOutputDto;
    }


    public void insertApp(Store store) {
        storeDao.insertStore(store);
    }

    public void updateApp(Store store) {
        storeDao.updateStore(store);
    }

    /**
     *
     * 功能描述: 根据appid查询商家信息和商品
     * @auther: wangbin
     * @date: 2018/8/16/016 16:46
     */
    public AbstractOutputDto queryStroeProductByAppid(App app) {
        AbstractOutputDto obj = new AbstractOutputDto();
        StoreProductBean storeProductBean = new StoreProductBean();
        List<Product> products = storeDao.queryProductByAppId(app.getId());
        Store store =storeDao.queryStoreByAppId(app.getId());
        storeProductBean.setStore(store);
        storeProductBean.setProducts(products);
        obj.setData(storeProductBean);
        return obj;
    }


}