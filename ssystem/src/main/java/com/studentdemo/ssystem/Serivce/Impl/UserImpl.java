package com.studentdemo.ssystem.Serivce.Impl;

import com.studentdemo.ssystem.DAO.UserDAO;
import com.studentdemo.ssystem.Enum.CodeEnum;
import com.studentdemo.ssystem.Exceptions.RegisterException;
import com.studentdemo.ssystem.Exceptions.RoleException;
import com.studentdemo.ssystem.POJO.UserPOJO;
import com.studentdemo.ssystem.POJO.UserRolePOJO;
import com.studentdemo.ssystem.Security.JsonResponse;
import com.studentdemo.ssystem.Serivce.IUser;
import com.studentdemo.ssystem.tools.get_ret_code;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "UserImpl")
public class UserImpl implements IUser {

    @Resource(name = "UserDAO")
    private UserDAO userDAO;

    @Override
    public JsonResponse userRegister(UserPOJO userPOJO) throws RegisterException {
        // 查询用户名或者密码是否被注册
        if (userDAO.countUserName(userPOJO) != null) {
            // 如果被注册抛出一个自定义异常，失败原因代码等在controller里定义了
            throw new RegisterException();
        }
        // 加密用户密码
        userPOJO.setPassword(new BCryptPasswordEncoder().encode(userPOJO.getPassword()));
        userDAO.userRegister(userPOJO);
        // 初始化用户角色
        userDAO.initUserRole(new UserRolePOJO(userPOJO.getUsername(), "USER/"));
        // 返回json，要注意其在登陆失败，登陆成功，未登录里使用的FastJson返回
        // 而这种能够被SpringBoot自动解析的对象就直接返回了
        return get_ret_code.response(CodeEnum.SUCCESS_REG.getMsg(), CodeEnum.SUCCESS_REG.getCode());
    }

    @Override
    public JsonResponse setUserRole(UserRolePOJO rolePOJO) {
        JsonResponse response;
        try {
            // 先获取原有角色字符串，再拼接上要添加的角色字符串
            rolePOJO.setRoles(userDAO.getUserRoles(rolePOJO.getUsername()) + rolePOJO.getRoles());
            userDAO.setUserRole(rolePOJO);
            response = get_ret_code.response(CodeEnum.SUCCESS_SET_ROLE.getMsg(), CodeEnum.SUCCESS_SET_ROLE.getCode());
        } catch (RoleException e) {
            response = get_ret_code.response(CodeEnum.FAIL_SET_ROLE.getMsg(), CodeEnum.FAIL_SET_ROLE.getCode());
        }
        return response;
    }
}
