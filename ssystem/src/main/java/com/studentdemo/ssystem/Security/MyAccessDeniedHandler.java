package com.studentdemo.ssystem.Security;

import com.alibaba.fastjson.JSON;
import com.studentdemo.ssystem.Enum.CodeEnum;
import com.studentdemo.ssystem.tools.get_ret_code;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "MyAccessDeniedHandler")
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(get_ret_code.response(CodeEnum.FAIL_ACCESS.getMsg(), CodeEnum.FAIL_ACCESS.getCode())));
    }
}
