package com.studentdemo.ssystem.Security;

import com.studentdemo.ssystem.DAO.UserDAO;
import com.studentdemo.ssystem.POJO.UserPOJO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component(value = "MyUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Resource(name = "UserDAO")
    private UserDAO userDAO;

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        // 注意：这里其为了方便设置的是用户权限，这样在使用时就能直接通过hasAuthority(Authority)
        // 的方式来判断用户权限，而如果设置的是用户角色，那么要在用户角色字符串前加上ROLE_标记，用
        // 来区分和权限的不同，但在使用时，通过hasRole(role)即可，无需再加标记
        // 假设数据库里的用户权限列表是用字符'/'隔开的，借此来对集合封装
        Arrays.stream(roles.split("/"))
                .forEach((str) -> authorityList.add(new SimpleGrantedAuthority(str)));
        return authorityList;
    }

    @Override
    public MyUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetail myUserDetail = new MyUserDetail();
        // 这里出现了获取对象为空的情况，原因是MyUserDetail这个类的get,set方法出了问题
        // 不知道什么原因使用了@Data注解但是还是写了get方法并且返回的null
        myUserDetail.setUsername(username);
        UserPOJO userPOJO = userDAO.userLogin(username);
        try {
            if (userPOJO == null) throw new UsernameNotFoundException(username);
            // 传入数据库里的密码
            myUserDetail.setPassword(userPOJO.getPassword());
            // 获取用户权限，顺便将其封装为一个满足Security投票要求的集合
            // 注意，这里其之前用的就是username，但因为设置用户使用邮箱或者用户名
            // 都可以成功登陆，而角色表中并没有邮箱这个字段所以需要把请求到的用户数据拿出来查询
            myUserDetail.setAuthorities(getAuthorities(userDAO.getUserRoles(userPOJO.getUsername())));
            return myUserDetail;
        } catch (Exception e) {
            // 防止返回空对象，在对比密码时就会自动登陆失败
            return new MyUserDetail();
        }
    }
}
