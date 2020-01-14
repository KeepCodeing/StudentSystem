package com.studentdemo.ssystem.Security;

import com.alibaba.fastjson.JSON;
import com.studentdemo.ssystem.Enum.CodeEnum;
import com.studentdemo.ssystem.tools.get_ret_code;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "FailHandle")
public class FailHandle implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // 设置响应头，否则会中文乱码
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        // 返回用户登陆失败json
        httpServletResponse.getWriter().write(JSON.toJSONString(get_ret_code.response(CodeEnum.FAIL_LOGIN.getMsg(), CodeEnum.FAIL_LOGIN.getCode())));
    }
}
