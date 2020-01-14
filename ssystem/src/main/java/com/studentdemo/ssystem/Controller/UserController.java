package com.studentdemo.ssystem.Controller;

import com.studentdemo.ssystem.Exceptions.RegisterException;
import com.studentdemo.ssystem.POJO.UserPOJO;
import com.studentdemo.ssystem.Security.JsonResponse;
import com.studentdemo.ssystem.Serivce.IUser;
import com.studentdemo.ssystem.Serivce.Impl.UserImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "UserImpl")
    private IUser iUser;

    @PostMapping("/register")
    public JsonResponse registerUser(@RequestBody UserPOJO userPOJO) throws RegisterException {
        return iUser.userRegister(userPOJO);
    }

}
