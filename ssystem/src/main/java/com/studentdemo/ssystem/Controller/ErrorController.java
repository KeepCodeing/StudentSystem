package com.studentdemo.ssystem.Controller;

import com.alibaba.fastjson.JSON;
import com.studentdemo.ssystem.Enum.CodeEnum;
import com.studentdemo.ssystem.Exceptions.RegisterException;
import com.studentdemo.ssystem.Exceptions.RoleException;
import com.studentdemo.ssystem.Security.JsonResponse;
import com.studentdemo.ssystem.tools.get_ret_code;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Api(tags = "返回错误信息的API")
@RestControllerAdvice
public class ErrorController {
    @ApiOperation(value = "用户注册失败")
    @ExceptionHandler(value = RegisterException.class)
    public JsonResponse registerException(RegisterException error) {
        // 用JSON返回用户名或邮箱已被注册的错误
        return get_ret_code.response(CodeEnum.FAIL_REG.getMsg(), CodeEnum.FAIL_REG.getCode());
    }

    @ApiOperation(value = "添加用户角色失败")
    @ExceptionHandler(value = RoleException.class)
    public JsonResponse roleException(RoleException e) {
        return get_ret_code.response(e.getMsg(), e.getCode());
    }
}
