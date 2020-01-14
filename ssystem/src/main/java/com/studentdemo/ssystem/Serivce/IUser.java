package com.studentdemo.ssystem.Serivce;

import com.studentdemo.ssystem.Exceptions.RegisterException;
import com.studentdemo.ssystem.POJO.UserPOJO;
import com.studentdemo.ssystem.Security.JsonResponse;

public interface IUser {
    // 用户登陆（无需我们实现，SpringSecurity已经实现了控制类）
    // UserPOJO userLogin(String username);
    // 用户注册
    JsonResponse userRegister(UserPOJO userPOJO) throws RegisterException;
}
