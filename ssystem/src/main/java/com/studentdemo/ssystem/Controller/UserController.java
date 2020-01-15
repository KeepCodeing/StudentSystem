package com.studentdemo.ssystem.Controller;

import com.studentdemo.ssystem.Exceptions.RegisterException;
import com.studentdemo.ssystem.Exceptions.RoleException;
import com.studentdemo.ssystem.POJO.UserPOJO;
import com.studentdemo.ssystem.POJO.UserRolePOJO;
import com.studentdemo.ssystem.Security.JsonResponse;
import com.studentdemo.ssystem.Serivce.IUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "操作用户信息的API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "UserImpl")
    private IUser iUser;

    @ApiOperation(value = "用户注册", notes = "用户登录交给SpringSecurity完成了")
    @PostMapping("/register")
    public JsonResponse registerUser(@RequestBody UserPOJO userPOJO) throws RegisterException {
        return iUser.userRegister(userPOJO);
    }

    @ApiOperation(value = "设置用户角色")
    @PostMapping("/setUserRole")
    public JsonResponse setUserRole(@RequestBody UserRolePOJO userRolePOJO) throws RoleException {
        return iUser.setUserRole(userRolePOJO);
    }

}
