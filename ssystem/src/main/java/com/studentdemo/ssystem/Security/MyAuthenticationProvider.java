package com.studentdemo.ssystem.Security;

import com.studentdemo.ssystem.Enum.CodeEnum;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component(value = "MyAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "MyUserDetailService")
    private MyUserDetailService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单中输入的用户名和密码
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDetails userInfo = userDetailService.loadUserByUsername(username);
        // 登陆失败有两种情况，用户名不对或密码不对
        // 用户名不对
        if (userInfo.getUsername() == null) {
            throw new UsernameNotFoundException(CodeEnum.FAIL_LOGIN.getMsg());
        }
        // 教程中直接对比加密字符串的方法是不可行的，因为该加密每次生成的密码都不一样
        // 要使用match方法对比原字符串和加密字符串才行，它返回一个布尔值，为真说明相同，否则不同
        // 注意：参数必须为未加密字符串，已加密字符串，否则会对比失败
        // 密码不对
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, userInfo.getPassword())) throw new BadCredentialsException(CodeEnum.FAIL_LOGIN.getMsg());
        return new UsernamePasswordAuthenticationToken(username, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 不这样写找不到UsernamePasswordAuthenticationToken，暂且蒙在鼓里
        return true;
    }
}
