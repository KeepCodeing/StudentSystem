package com.studentdemo.ssystem.Security;

import com.alibaba.fastjson.JSON;
import com.studentdemo.ssystem.Enum.CodeEnum;
import com.studentdemo.ssystem.tools.get_ret_code;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "SuccessHandle")
public class SuccessHandle implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 设置响应头，否则会中文乱码
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        // 登陆成功返回json
        httpServletResponse.getWriter().write(JSON.toJSONString(get_ret_code.response(CodeEnum.SUCCESS_LOGIN.getMsg(), CodeEnum.SUCCESS_LOGIN.getCode())));
    }
}
