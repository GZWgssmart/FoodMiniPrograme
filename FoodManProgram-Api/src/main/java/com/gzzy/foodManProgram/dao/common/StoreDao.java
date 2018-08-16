package com.gzzy.foodManProgram.dao.common;

import com.gzzy.foodManProgram.dao.common.provide.CommonProvide;
import com.gzzy.foodManProgram.entity.common.Store;
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
            " tel, " +
            " log, " +
            " video," +
            " appid," +
            " type," +
            " createtime)values ( " +
            " #{id}, " +
            " #{name}," +
            " #{address}," +
            " #{des}," +
            " #{tel}," +
            " #{log}," +
            " #{video}, " +
            " #{appid}, " +
            " #{type}," +
            " now())")
    void insertStore(Store store);

    @Update("update t_store set " +
            " name = #{name}," +
            " address = #{address}," +
            " des = #{des}," +
            " tel = #{tel}," +
            " log = #{log}," +
            " video = #{video}," +
            " appid = #{appid}," +
            " type = #{type} where id = #{id}")
    void updateStore(Store store);

    //门店列表
    @SelectProvider(type = CommonProvide.class,method = "queryAppAll")
    List<Store> queryStoreAll(@Param("sidx") String sidx, @Param("sord") String sord,
                              @Param("startnum") int startnum, @Param("endnum") int endnum, Store store);


    //门店列表
    @SelectProvider(type = CommonProvide.class,method = "queryAppAllCount")
    int queryStoreAllCount(Store store);
}
