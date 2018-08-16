package com.gzzy.foodManProgram.service.common;

import com.gzzy.foodManProgram.dao.common.UserDao;
import com.gzzy.foodManProgram.entity.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> queryUser() {
        List<User> users =  userDao.querUser();
        return users;

    }
}
