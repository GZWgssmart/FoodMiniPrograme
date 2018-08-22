package com.gzzy.foodManProgram.dao.common.provide;

import com.gzzy.foodManProgram.dto.ProductDto;
import com.gzzy.foodManProgram.dto.StoreDto;
import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.entity.common.Store;
import com.gzzy.foodManProgram.util.Util;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @ClassName CommonProvide
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/14/014 19:20
 * @Version 1.0
 */
public class CommonProvide {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommonProvide() {
    }

    //小程序app
    public String queryAppAll(@Param("sidx") String sidx, @Param("sord") String sord,
                                   @Param("startnum") int startnum, @Param("endnum") int endnum, App app){
        StringBuilder sql=new StringBuilder();
        sql.append(" select * from t_app where 1=1");

        if(!Objects.isNull(app.getName())&&!"".equals(app.getName())){
            sql.append(" and name like '%").append(app.getName()).append("%'");
        }
        if(!Util.isEmpty(sidx)&&!Util.isEmpty(sord)){
            sql.append(" order by ").append(sidx).append(" ").append(sord);
        }else{
            sql.append(" order by createtime desc ");
        }
        sql.append(" limit ").append(startnum+" , ").append(endnum);
        logger.info(sql.toString());
        return sql.toString();
    }

    public String queryAppAllCount(App app){
        StringBuilder sql=new StringBuilder();
        sql.append(" select count(*) from t_app where 1=1");

        if(!Objects.isNull(app.getName())&&!"".equals(app.getName())){
            sql.append(" and name like '%").append(app.getName()).append("%'");
        }

        logger.info(sql.toString());
        return sql.toString();
    }

    //小程序app
    public String queryStoreAll(@Param("sidx") String sidx, @Param("sord") String sord,
                                @Param("startnum") int startnum, @Param("endnum") int endnum, StoreDto storeDto){
        StringBuilder sql=new StringBuilder();
        sql.append(" select s.*, a.name as appname from t_store s left join t_app a on a.id = s.appid where 1=1 ");
        if(!Objects.isNull(storeDto.getName())&&!"".equals(storeDto.getName())){
            sql.append(" and s.name like '%").append(storeDto.getName()).append("%'");
        }
        if(!Objects.isNull(storeDto.getTel())&&!"".equals(storeDto.getTel())){
            sql.append(" and s.tel like '%").append(storeDto.getTel()).append("%'");
        }
        if (!Objects.isNull(storeDto.getAppid()) && storeDto.getAppid() > 0) {
            sql.append(" and s.appid =  ").append(storeDto.getAppid());
        }
        if(!Objects.isNull(storeDto.getStatus())&&!"".equals(storeDto.getStatus())){
            sql.append(" and s.status =  '").append(storeDto.getStatus()+ "'");
        }
        if(!Objects.isNull(storeDto.getType())&&!"".equals(storeDto.getType())){
            sql.append(" and s.type =  '").append(storeDto.getType()+ "'");
        }
        if(!Util.isEmpty(sidx)&&!Util.isEmpty(sord)){
            sql.append(" order by ").append(sidx).append(" ").append(sord);
        }else{
            sql.append(" order by s.createtime desc ");
        }
        sql.append(" limit ").append(startnum+" , ").append(endnum);
        logger.info(sql.toString());
        return sql.toString();
    }

    public String queryStoreAllCount(StoreDto storeDto){
        StringBuilder sql=new StringBuilder();
        sql.append(" select count(s.id) from t_store s left join t_app a on a.id = s.appid where 1=1 ");

        if(!Objects.isNull(storeDto.getName())&&!"".equals(storeDto.getName())){
            sql.append(" and s.name like '%").append(storeDto.getName()).append("%'");
        }

        if(!Objects.isNull(storeDto.getTel())&&!"".equals(storeDto.getTel())){
            sql.append(" and s.tel like '%").append(storeDto.getTel()).append("%'");
        }

        if (!Objects.isNull(storeDto.getAppid()) && storeDto.getAppid() > 0) {
            sql.append(" and s.appid =  ").append(storeDto.getAppid());
        }

        if(!Objects.isNull(storeDto.getStatus())&&!"".equals(storeDto.getStatus())){
            sql.append(" and s.status =  '").append(storeDto.getStatus()+ "'");
        }

        if(!Objects.isNull(storeDto.getType())&&!"".equals(storeDto.getType())){
            sql.append(" and s.type =  '").append(storeDto.getType()+ "'");
        }

        logger.info(sql.toString());
        return sql.toString();
    }

    //商品app
    public String queryProductAll(@Param("sidx") String sidx, @Param("sord") String sord,
                                @Param("startnum") int startnum, @Param("endnum") int endnum, ProductDto productDto){
        StringBuilder sql=new StringBuilder();
        sql.append(" select a.name as appname,s.name as storename, p.* from t_product p left join t_store s on p.sid = s.id left join t_app a on a.id = p.appid  where 1=1 ");

        if(!Objects.isNull(productDto.getName())&&!"".equals(productDto.getName())){
            sql.append(" and p.name like '%").append(productDto.getName()).append("%'");
        }

        if(!Objects.isNull(productDto.getTitle())&&!"".equals(productDto.getTitle())){
            sql.append(" and p.title like '%").append(productDto.getTitle()).append("%'");
        }

        if (!Objects.isNull(productDto.getAppid()) && productDto.getAppid() > 0) {
            sql.append(" and p.appid =  ").append(productDto.getAppid());
        }

        if (!Objects.isNull(productDto.getSid()) && productDto.getSid() > 0) {
            sql.append(" and p.sid =  ").append(productDto.getSid());
        }

        if(!Objects.isNull(productDto.getStatus())&&!"".equals(productDto.getStatus())){
            sql.append(" and p.status =  '").append(productDto.getStatus()+ "'");
        }

        if (!Objects.isNull(productDto.getPricestart())&&!"".equals(productDto.getPricestart())) {
            BigDecimal pricestart=new BigDecimal( productDto.getPricestart());
            if(pricestart.compareTo(BigDecimal.ZERO)!=0){
                sql.append(" and p.price >= ").append( pricestart);
            }
        }

        if (!Objects.isNull(productDto.getPriceend())&&!"".equals(productDto.getPriceend())) {
            BigDecimal priceend=new BigDecimal( productDto.getPriceend());
            if(priceend.compareTo(BigDecimal.ZERO)!=0){
                sql.append(" and p.price <= ").append(priceend);
            }
        }






        if(!Util.isEmpty(sidx)&&!Util.isEmpty(sord)){
            sql.append(" order by ").append(sidx).append(" ").append(sord);
        }else{
            sql.append(" order by p.createtime desc ");
        }
        sql.append(" limit ").append(startnum+" , ").append(endnum);
        logger.info(sql.toString());
        return sql.toString();
    }

    public String queryProductAllCount(ProductDto productDto){
        StringBuilder sql=new StringBuilder();
        sql.append(" select count(p.id) from t_product p left join t_store s on p.sid = s.id left join t_app a on a.id = p.appid  where 1=1");



        if(!Objects.isNull(productDto.getName())&&!"".equals(productDto.getName())){
            sql.append(" and p.name like '%").append(productDto.getName()).append("%'");
        }

        if(!Objects.isNull(productDto.getTitle())&&!"".equals(productDto.getTitle())){
            sql.append(" and p.title like '%").append(productDto.getTitle()).append("%'");
        }

        if (!Objects.isNull(productDto.getAppid()) && productDto.getAppid() > 0) {
            sql.append(" and p.appid =  ").append(productDto.getAppid());
        }

        if (!Objects.isNull(productDto.getSid()) && productDto.getSid() > 0) {
            sql.append(" and p.sid =  ").append(productDto.getSid());
        }

        if(!Objects.isNull(productDto.getStatus())&&!"".equals(productDto.getStatus())){
            sql.append(" and p.status =  '").append(productDto.getStatus()+ "'");
        }


        if (!Objects.isNull(productDto.getPricestart())&&!"".equals(productDto.getPricestart())) {
            BigDecimal pricestart=new BigDecimal( productDto.getPricestart());
            if(pricestart.compareTo(BigDecimal.ZERO)!=0){
                sql.append(" and p.price >= ").append( pricestart);
            }
        }

        if (!Objects.isNull(productDto.getPriceend())&&!"".equals(productDto.getPriceend())) {
            BigDecimal priceend=new BigDecimal( productDto.getPriceend());
            if(priceend.compareTo(BigDecimal.ZERO)!=0){
                sql.append(" and p.price <= ").append(priceend);
            }
        }
        logger.info(sql.toString());
        return sql.toString();
    }
}
