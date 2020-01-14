package com.studentdemo.ssystem.config_t;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// @Service
public class UserConfig implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Resource(name = "UserTDAO")
    // private UserTDAO userTDAO;

    private Collection<GrantedAuthority> grantedAuthority(List<String> roles) {
        // 分配角色/权限应该根据DAO的查询来
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 因为GrantedAuthority是个接口，所以这里用SimpleGrantedAuthority类创建了它实现类
        roles.forEach((str) -> grantedAuthorities.add(new SimpleGrantedAuthority(str)));
        return grantedAuthorities;
    }

    // 坠毁
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        // 注意这里强转成ArrayList的这个集合，它保存的是用户的角色信息
        // 如果某个用户只有一个角色属性，使用AuthorityUtils.commaSeparatedStringToAuthorityList(String)
        // 这个工具类即可
        return new User("yjsp", passwordEncoder.encode("114514"), new ArrayList<GrantedAuthority>());

        // UserTPOJO user = userTDAO.findUserByName(username);
        // System.out.println(user+"------------");
        // Collection<? extends GrantedAuthority> authorities;
        // authorities.add("test");
    }
}
