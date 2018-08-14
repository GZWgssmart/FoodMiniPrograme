package com.gzzy.foodManProgram.dao.common;

import com.gzzy.foodManProgram.entity.common.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
