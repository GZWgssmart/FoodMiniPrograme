package com.gzzy.foodManProgram.dao.common;

import com.gzzy.foodManProgram.dao.common.provide.CommonProvide;
import com.gzzy.foodManProgram.dto.StoreDto;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.entity.common.Store;
import com.gzzy.foodManProgram.entity.common.bean.ValueLabel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName StoreDao
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/14/014 19:53
 * @Version 1.0
 */
@Mapper
public interface StoreDao {

    @Insert("insert into t_store(" +
            " id," +
            " name, " +
            " address, " +
            " des," +
            " longitude," +
            " latitude," +
            " tel, " +
            " logo, " +
            " video," +
            " appid," +
            " type," +
            " createtime)values ( " +
            " #{id}, " +
            " #{name}," +
            " #{address}," +
            " #{des}," +
            " #{longitude}," +
            " #{latitude}," +
            " #{tel}," +
            " #{logo}," +
            " #{video}, " +
            " #{appid}, " +
            " #{type}," +
            " now())")
    void insertStore(Store store);

    @Update("update t_store set " +
            " name = #{name}," +
            " address = #{address}," +
            " des = #{des}," +
            " longitude = #{longitude}," +
            " latitude = #{latitude}," +
            " tel = #{tel}," +
            " logo = #{logo}," +
            " video = #{video}," +
            " appid = #{appid}," +
            " type = #{type} where id = #{id}")
    void updateStore(Store store);

    //门店列表
    @SelectProvider(type = CommonProvide.class,method = "queryStoreAll")
    List<Store> queryStoreAll(@Param("sidx") String sidx, @Param("sord") String sord,
                              @Param("startnum") int startnum, @Param("endnum") int endnum, StoreDto storeDto);


    //门店列表
    @SelectProvider(type = CommonProvide.class,method = "queryStoreAllCount")
    int queryStoreAllCount(StoreDto storeDto);

    /**
     *
     * 功能描述: 根据appid查询商家
     * @auther: wangbin
     * @date: 2018/8/16/016 16:46
     */
    @Select("select * from t_store where appid = #{appid}")
    Store queryStoreByAppId(@Param("appid") int appid);


    /**
     *
     * 功能描述: 根据appid查询商品
     * @auther: wangbin
     * @date: 2018/8/16/016 16:46
     */
    @Select("select * from t_product where appid = #{appid}")
    List<Product> queryProductByAppId(@Param("appid") int appid);

    /**
     *
     * 功能描述: 根据商家id查询商品
     * @auther: wangbin
     * @date: 2018/8/16/016 16:46
     */
    @Select("select * from t_product where sid = #{storeid}")
    List<Product> queryStroeProductByid(@Param("storeid") int storeid);


    /**
     *
     * 功能描述: 禁用
     * @auther: wangbin
     * @date: 2018/8/22/022 15:47
     */
    @Update("update t_store set status = '禁用' where id = #{id}")
    void disable(@Param("id") int id);

    /**
     *
     * 功能描述: 启用
     * @auther: wangbin
     * @date: 2018/8/22/022 15:47
     */
    @Update("update t_store set status = '启用' where id = #{id}")
    void enabled(@Param("id") int id);


    /**
     *
     * 功能描述: 根据appid查询所有商家
     * @auther: wangbin
     * @date: 2018/8/22/022 17:09
     */

    @Select("select s.id as value, s.name as label from t_store s left join t_app a on s.appid = a.id where s.appid = #{appid} and status = '启用'")
    List<ValueLabel> queryStoreValueLabelByAppid(@Param("appid") int appid);

    /**
     *
     * 功能描述: 根据appid查询所有商家
     * @auther: wangbin
     * @date: 2018/8/22/022 17:09
     */

    @Select("select s.id as value, s.name as label from t_store s  where  status = '启用'")
    List<ValueLabel> queryStoreValueLabel();

}
