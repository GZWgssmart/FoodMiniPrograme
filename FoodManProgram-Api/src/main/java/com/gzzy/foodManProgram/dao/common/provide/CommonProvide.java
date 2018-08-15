package com.gzzy.foodManProgram.dao.common.provide;

import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.Store;
import com.gzzy.foodManProgram.util.Util;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                              @Param("startnum") int startnum, @Param("endnum") int endnum, Store store){
        StringBuilder sql=new StringBuilder();
        sql.append(" select * from t_store where 1=1");

        if(!Objects.isNull(store.getName())&&!"".equals(store.getName())){
            sql.append(" and name like '%").append(store.getName()).append("%'");
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

    public String queryStoreAllCount(Store store){
        StringBuilder sql=new StringBuilder();
        sql.append(" select count(*) from t_store where 1=1");

        if(!Objects.isNull(store.getName())&&!"".equals(store.getName())){
            sql.append(" and name like '%").append(store.getName()).append("%'");
        }

        logger.info(sql.toString());
        return sql.toString();
    }
}
