package com.gzzy.foodManProgram.controller.common;

import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.entity.common.User;
import com.gzzy.foodManProgram.service.common.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUser",method = RequestMethod.POST)
    @ApiOperation(value = "查询用户",notes = "查询用户")
    public AbstractOutputDto queryUser(){
        AbstractOutputDto obj = new AbstractOutputDto();
        List<User> users = userService.queryUser();
        obj.setDatalist(users);
        return obj;
    }
}
