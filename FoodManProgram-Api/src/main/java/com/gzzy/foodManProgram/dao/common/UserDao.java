package com.gzzy.foodManProgram.dao.common;

import com.gzzy.foodManProgram.entity.common.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from t_user")
    List<User> querUser();


}
