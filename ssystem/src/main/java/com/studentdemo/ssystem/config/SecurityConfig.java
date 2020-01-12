package com.studentdemo.ssystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 首先使用Configuration注解把该类加载进容器并且告诉容器这是个配置类
@Configuration
// 再使用自动配置，必须写法
@EnableWebSecurity
// 让该类继承这个抽象类，并且实现configure(HttpSecurity http)这个方法
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 当用户无权访问时，以表单验证这种方式提醒用户
        http.formLogin()
                .and()
                .authorizeRequests()
                // 声明在这个url下的请求不需要验证
                .antMatchers("/test").permitAll()
                // 声明其他全部请求都需要身份验证
                .anyRequest().authenticated();
    }
}
/*
* Security的运行过程（大概）
* 总的来说可以分为三大部分
* 1. 判断用户请求（SecurityContext）中（可能是session，cookie）是否带有登陆成功的信息，如果有，跳过第二步
* 2. 将用户传递过来的信息进行筛选，我们可以选择使用什么筛选方式，Security提供了十多种筛选方式
* 它们连在一起，形成了一个筛选链，筛选过程中如果有筛选器所需信息，将其加入SecurityContext，并
* 停止筛选到第三步，如果没有，继续筛选，直到所有筛选器都走完了
* 3. 如果用户信息已经验证（SecurityContext中有东西），判断用户是否经过认证，以及是否有权访问，
* 如果两项中有任何一项不满足，根据配置进行响应，相反允许访问
*
* 通过Security提供的对象在代码中获取用户登陆信息
* SecurityContextHolder.getContext().getAuthentication()可以返回一个Authentication类
* 这个类存放的是用户验证的信息，包括了用户验证类型，用户名，密码等。如果用户访问的是一个无
* 需验证的API，那么用户类型可能就是anonymousUser，相反，则会返回用户验证的类型，如用户名密
* 码登陆返回的是User类型。这个类还提供了getPrincipal()方法，专门用来获取用户验证类型。
*
* Basic认证：通过base64加密后的请求头来弹出窗口提示用户输入用户名和密码，再将用户名和密码通过base64
* 加密的方式返回给服务器，服务器再解密对比
*
* 实现自定义表单登陆界面
* 将.loginPage(path)加入到过滤链...(前后端分离暂不考虑）
* */
