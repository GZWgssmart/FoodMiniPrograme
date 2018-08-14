package com.gzzy.foodManProgram.dao.common;

import com.gzzy.foodManProgram.dao.common.provide.CommonProvide;
import com.gzzy.foodManProgram.entity.common.App;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName AppDao
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/14/014 19:09
 * @Version 1.0
 */
@Mapper
public interface AppDao {

    //门店列表
    @SelectProvider(type = CommonProvide.class,method = "queryAppAll")
    List<App> queryAppAll(@Param("sidx") String sidx, @Param("sord") String sord,
                                           @Param("startnum") int startnum, @Param("endnum") int endnum, App app);


    //门店列表
    @SelectProvider(type = CommonProvide.class,method = "queryAppAllCount")
    int queryAppAllCount(App app);

    @Insert("insert into t_app(id,name,des,createtime) values (#{id}, #{name},#{des},now())")
    void insertApp(App app);

    @Update("update t_app set name=#{name}, des = #{des} where id = #{id}")
    void updateApp(App app);


}
