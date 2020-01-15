package com.studentdemo.ssystem.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class MainConfig extends WebSecurityConfigurerAdapter {

    // 定义登陆成功怎么处理
    @Resource(name = "SuccessHandle")
    private SuccessHandle successHandle;

    // 定义登陆失败怎么处理
    @Resource(name = "FailHandle")
    private FailHandle failHandle;

    // 使用自定义的安全校验规则
    @Resource(name = "MyAuthenticationProvider")
    private MyAuthenticationProvider detailService;

    // 自定义用户未登录返回内容，否则默认返回html
    @Resource(name = "MyAuthenticationEntryPoint")
    private MyAuthenticationEntryPoint entryPoint;

    // 自定义无权访问返回提示
    @Resource(name = "MyAccessDeniedHandler")
    private MyAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义的用户校验方法处理登陆请求
        auth.authenticationProvider(detailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许访问的api
        http.authorizeRequests()
                .antMatchers("/user/register", "/login").permitAll()
                .antMatchers("/index", "/").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/api/**").hasAuthority("ADMIN")
                // 设置不拦截swagger
                .antMatchers("/swagger*/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll();

        // 不允许访问其他api
        http.authorizeRequests().anyRequest().authenticated();

        // 登陆方式（注意，这里的请求头要设置成表单请求头）
        http.formLogin()
                .successHandler(successHandle)
                .failureHandler(failHandle);

        // 注销
        http.logout().logoutSuccessUrl("/index");

        // 未登录访问资源返回Json提示用户登陆
        http.exceptionHandling().authenticationEntryPoint(entryPoint);

        // 无权访问返回提示
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        // 关闭跨站攻击防护，方便调试
        http.csrf().disable();
    }
}
