package com.gzzy.foodManProgram.dao.common;

import com.gzzy.foodManProgram.dao.common.provide.CommonProvide;
import com.gzzy.foodManProgram.dto.ProductDto;
import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.Product;
import com.gzzy.foodManProgram.entity.common.bean.ValueLabel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName ProductDao
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/15/015 12:50
 * @Version 1.0
 */
@Mapper
public interface ProductDao {

    //商品列表
    @SelectProvider(type = CommonProvide.class,method = "queryProductAll")
    List<Product> queryProductAll(@Param("sidx") String sidx, @Param("sord") String sord,
                          @Param("startnum") int startnum, @Param("endnum") int endnum, ProductDto productDto);



    @SelectProvider(type = CommonProvide.class,method = "queryProductAllCount")
    int queryProductAllCount(ProductDto productDto);


    @Insert("insert into t_product (" +
            " id," +
            " sid," +
            " name," +
            " title," +
            " price," +
            " des," +
            " img," +
            " imgdetail," +
            " appid," +
            " createtime) values " +
            "(#{id}," +
            " #{sid}," +
            " #{name}," +
            " #{title}," +
            " #{price}," +
            " #{des}," +
            " #{img}," +
            " #{imgdetail}," +
            " #{appid}," +
            " now())")
    void insertProduct(Product product);

    @Update("update t_product set " +
            " name = #{name}," +
            " title = #{title}," +
            " price = #{price}," +
            " des = #{des}," +
            " img = #{img}," +
            " imgdetail = #{imgdetail}," +
            " appid = #{appid} where id = #{id}")
    void updateProduct(Product product);
    
    /**
     *
     * 功能描述: 禁用
     * @auther: wangbin
     * @date: 2018/8/22/022 15:47
     */
    @Update("update t_product set status = '禁用' where id = #{id}")
    void disable(@Param("id") int id);

    /**
     *
     * 功能描述: 启用
     * @auther: wangbin
     * @date: 2018/8/22/022 15:47
     */
    @Update("update t_product set status = '启用' where id = #{id}")
    void enabled(@Param("id") int id);




}
