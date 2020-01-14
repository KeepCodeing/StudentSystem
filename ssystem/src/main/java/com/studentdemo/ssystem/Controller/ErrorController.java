package com.studentdemo.ssystem.Controller;

import com.alibaba.fastjson.JSON;
import com.studentdemo.ssystem.Enum.CodeEnum;
import com.studentdemo.ssystem.Exceptions.RegisterException;
import com.studentdemo.ssystem.Security.JsonResponse;
import com.studentdemo.ssystem.tools.get_ret_code;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = RegisterException.class)
    public JsonResponse registerException(RegisterException error) {
        // 用JSON返回用户名或邮箱已被注册的错误
        return get_ret_code.response(CodeEnum.FAIL_REG.getMsg(), CodeEnum.FAIL_REG.getCode());
    }
}
