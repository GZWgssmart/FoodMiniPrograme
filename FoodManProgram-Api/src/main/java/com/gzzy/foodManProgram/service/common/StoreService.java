package com.gzzy.foodManProgram.service.common;

import com.gzzy.foodManProgram.dao.common.StoreDao;
import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.dto.StoreDto;
import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.entity.common.Store;
import com.gzzy.foodManProgram.entity.common.StoreProductBean;
import com.gzzy.foodManProgram.entity.common.bean.ValueLabel;
import org.apache.commons.lang.StringUtils;
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

    public AbstractOutputDto queryStoreAll(AbstractInputDto<StoreDto> parmeter) {
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


    public AbstractOutputDto insertStore(Store store) {
        AbstractOutputDto abstractOutputDto= new AbstractOutputDto();
       List<ValueLabel> valueLabels = storeDao.queryStoreValueLabelByAppid(store.getAppid());
       if (valueLabels.size() == 0) {
           storeDao.insertStore(store);
           abstractOutputDto.setMsg("添加成功");
       } else {
           abstractOutputDto.setMsg("该小程序已经存在商家");
       }
       return abstractOutputDto;
    }

    public void updateStore(Store store) {
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
        Store store =storeDao.queryStoreByAppId(app.getAppid());
        storeProductBean.setStore(store);
        storeProductBean.setProducts(products);
        obj.setData(storeProductBean);
        return obj;
    }

    /**
     *
     * 功能描述: 根据storeid查询商品
     * @auther: wangbin
     * @date: 2018/8/16/016 16:46
     */
    public AbstractOutputDto queryStroeProductByid(Store store) {
        AbstractOutputDto obj = new AbstractOutputDto();
        List<Product> products =storeDao.queryStroeProductByid(store.getId());
        obj.setDatalist(products);
        return obj;
    }

    /**
     *
     * 功能描述: 根据商品id查询商品详情
     * @auther: wangbin
     * @date: 2018/8/16/016 16:46
     */
    public AbstractOutputDto queryProductByid(Product product) {
        AbstractOutputDto obj = new AbstractOutputDto();
        Product product1 =storeDao.queryProductByid(product.getId());
        obj.setData(product1);
        return obj;
    }

    /**
     *
     * 功能描述: 启用禁用
     * @auther: wangbin
     * @date: 2018/8/22/022 15:53
     */
    public void enableddisable(Store store) {
        if(store != null) {
            if (StringUtils.isNotEmpty(store.getStatus())) {
                if ("禁用".equals(store.getStatus())) {
                    storeDao.enabled(store.getId());
                }else {
                    storeDao.disable(store.getId());
                }
            }
        }

    }

    /**
     *
     * 功能描述:根据appid查询所有商家
     * @auther: wangbin
     * @date: 2018/8/22/022 17:14
     */
    public List<ValueLabel> queryStoreValueLabelByAppid(String appid) {
        List<ValueLabel> valueLabels = storeDao.queryStoreValueLabelByAppid(appid);
        return valueLabels;
    }

    /**
     *
     * 功能描述:查询所有商家
     * @auther: wangbin
     * @date: 2018/8/22/022 17:14
     */
    public List<ValueLabel> queryStoreValueLabel() {
        List<ValueLabel> valueLabels = storeDao.queryStoreValueLabel();
        return valueLabels;
    }



}
