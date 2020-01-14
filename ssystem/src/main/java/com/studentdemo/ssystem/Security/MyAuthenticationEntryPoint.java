package com.studentdemo.ssystem.Security;

import com.alibaba.fastjson.JSON;
import com.studentdemo.ssystem.Enum.CodeEnum;
import com.studentdemo.ssystem.tools.get_ret_code;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "MyAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    @ResponseBody
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // 设置响应头，否则会中文乱码
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        // 返回用户未登录
        httpServletResponse.getWriter().write(JSON.toJSONString(get_ret_code.response(CodeEnum.USER_NO_LOGIN.getMsg(), CodeEnum.USER_NO_LOGIN.getCode())));
    }
}
