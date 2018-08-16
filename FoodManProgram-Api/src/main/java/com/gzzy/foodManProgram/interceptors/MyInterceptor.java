package com.gzzy.foodManProgram.interceptors;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gzzy.foodManProgram.common.Authinfo;
import com.gzzy.foodManProgram.common.Config;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.logs.LogManager;
import com.gzzy.foodManProgram.service.common.TokenService;
import com.gzzy.foodManProgram.util.HttpClientUtil;
import com.gzzy.foodManProgram.util.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private Config config;
    private TokenService tokenService;

    public MyInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        LogManager.info("end");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        LogManager.info(response.getContentType());
        LogManager.info("post");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object modelAndView) throws Exception {

        if ("options".equalsIgnoreCase(request.getMethod())) {
            return false;
        }


        Map<String, String[]> parameterMap = request.getParameterMap();


        for (String s : parameterMap.keySet()) {
//            if(!s.equals("file"))
//            LogManager.info("key:" + s + ",value:" + StringUtils.join(parameterMap.get(s)));
        }


        //判断是否从swagger中访问
        String referer = request.getHeader("Referer");
        if (StringUtils.defaultString(referer).indexOf("swagger-ui.html") != -1) {
            return true;
        }

        String auth = request.getHeader("auth");

        if (StringUtils.isBlank(auth)) {
            AbstractOutputDto rtndata = new AbstractOutputDto<>();
            rtndata.setStatus(-1);
            rtndata.setMsg("暂未登录");
            String json = JSON.toJSONString(rtndata);
            response.setContentType("application/json;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.getWriter().print(json);
            return false;
        }

        Authinfo authinfo = HttpClientUtil.getRequestdate(auth, new TypeReference<Authinfo>() {
        });


        if (Util.isEmpty(authinfo.getAccesstoken())) {
            AbstractOutputDto rtndata = new AbstractOutputDto<>();
            rtndata.setStatus(-1);
            rtndata.setMsg("暂未登录");
            String json = JSON.toJSONString(rtndata);
            response.setContentType("application/json;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.getWriter().print(json);
            return false;
        } else {
            //boolean valid = tokenService.checkToken(authinfo.getAccesstoken(),authinfo.getStatus());
            boolean valid = true;
            if (!valid) {
                AbstractOutputDto rtndata = new AbstractOutputDto<>();
                rtndata.setStatus(-1);
                rtndata.setMsg("无效登录信息");
                String json = JSON.toJSONString(rtndata);
                response.setContentType("application/json;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                response.getWriter().print(json);
            }
            return valid;
        }

//        return true;
    }

}
