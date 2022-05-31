package com.studentdemo.ssystem.config_t;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource;
//
//// 首先使用Configuration注解把该类加载进容器并且告诉容器这是个配置类
//// @Configuration
//// 再使用自动配置，必须写法
//// @EnableWebSecurity
//// 让该类继承这个抽象类，并且实现configure(HttpSecurity http)这个方法
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    // 为了实现自定义用户校验，需要覆盖这个configure方法，重写了这个方法后
//    // 的校验方式就不是按照配置文件里的默认用户名和密码来了，而是根据用户实
//    // 现的校验类来（basicHttp验证方式的实现）
//    /*@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 在这里要实现userDetailsService这个接口来提供验证功能
//        auth.userDetailsService(new UserConfig()).passwordEncoder(passwordEncoder());
//    }*/
//    // 新版本写法
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("yjsp").password(passwordEncoder().encode("114514")).authorities("ROLE_USER");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // 注入数据源对象
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public PersistentTokenRepository tokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        // 设置启动服务就在数据库中创建一张表用来存储token
//        // 如果是二次启动而表已经被创建过了会报错，所以二次启动要注释掉
//        // jdbcTokenRepository.setCreateTableOnStartup(true);
//        // 设置数据源，这里我们使用的是SpringBoot已经配置好了的
//        jdbcTokenRepository.setDataSource(dataSource);
//        return jdbcTokenRepository;
//    }
//
//    /*
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 当用户无权访问时，以表单验证这种方式提醒用户
//        http.formLogin()
//                .and()
//                .authorizeRequests()
//                // 声明在这个url下的请求不需要验证
//                .antMatchers("/test").permitAll()
//                // 声明其他全部请求都需要身份验证
//                .anyRequest().authenticated()
//                // 实现记住我功能：浏览器用cookie存放一个token，
//                // tokenRepository方法需要使用一个我们配置的数据源方法
//                // 前端提交数据的时候控件的name属性默认为remember-me
//                // 加了记住我功能后formLogin验证模式会自动生成这个CheckBox
//                 .and().
//                 rememberMe()
//                 .tokenRepository(tokenRepository())
//                // 记住我的时间，单位为秒
//                .tokenValiditySeconds(10)
//                .and()
//                // 关于session存活时间：可以在配置文件中配置，过期了则需要用户
//                // 重新登陆，一般会写个页面让用户重新登陆
//                // 设置最大session数，超过最大session数则会让之前的session失效
//                // 可以做到控制用户登陆的效果
//                .sessionManagement()
//                .maximumSessions(1)
//                // 如果加了这个设置，超过最大session数时用户就无法登陆了
//                .maxSessionsPreventsLogin(true);
//    }*/
//    // 实现basicHttp认证（坠毁，教程的写法有问题，只能copy了一堆东西）
//
//    // 在5.x的版本中，要使用basicHttp的验证方法必须提供一个BasicAuthenticationEntryPoint
//    // 类
//    @Autowired
//    private BasicHttpTest basicHttpTest;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
//                .authenticationEntryPoint(basicHttpTest)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest()
//                .authenticated();
//    }
//}
///*
//* Security的运行过程（大概）
//* 总的来说可以分为三大部分
//* 1. 判断用户请求（SecurityContext）中（可能是session，cookie）是否带有登陆成功的信息，如果有，跳过第二步
//* 2. 将用户传递过来的信息进行筛选，我们可以选择使用什么筛选方式，Security提供了十多种筛选方式
//* 它们连在一起，形成了一个筛选链，筛选过程中如果有筛选器所需信息，将其加入SecurityContext，并
//* 停止筛选到第三步，如果没有，继续筛选，直到所有筛选器都走完了
//* 3. 如果用户信息已经验证（SecurityContext中有东西），判断用户是否经过认证，以及是否有权访问，
//* 如果两项中有任何一项不满足，根据配置进行响应，相反允许访问
//*
//* 通过Security提供的对象在代码中获取用户登陆信息
//* SecurityContextHolder.getContext().getAuthentication()可以返回一个Authentication类
//* 这个类存放的是用户验证的信息，包括了用户验证类型，用户名，密码等。如果用户访问的是一个无
//* 需验证的API，那么用户类型可能就是anonymousUser，相反，则会返回用户验证的类型，如用户名密
//* 码登陆返回的是User类型。这个类还提供了getPrincipal()方法，专门用来获取用户验证类型。
//*
//* Basic认证：通过base64加密后的请求头来弹出窗口提示用户输入用户名和密码，再将用户名和密码通过base64
//* 加密的方式返回给服务器，服务器再解密对比
//*
//* 实现自定义表单登陆界面
//* 将.loginPage(path)加入到过滤链...(前后端分离暂不考虑），因为与教程版本不同所以一直写不出来
//*
//* 实现自定义用户检验
//* 1. 加密密码
//* 5.x版本想要自定义用户认证方法，必须要保证UserDetails接口返回的对象中password属性为加密了的，否则
//* 会报错，我们可以创建一个Bean对象，专门用来生成加密对象，在用的时候注入即可
//* 注意：在实际开发环境中数据库的密码都是已经加密过了的，所以就不需要再像测试那样加密明文再解析了
//* 2. 添加用户权限
//* 添加权限使用的是一个GrantedAuthority集合，当用户只有一个权限/角色时，可以使用使用
//* AuthorityUtils.commaSeparatedStringToAuthorityList(String)工具类将字符串转
//* 换为该类型。当用户有多种权限/角色时，最后构造一个方法专门用来封装这个集合（小技巧，
//* 可以用Ctrl+H的方法查看接口的实现类）
//* 3. 设置API访问权限
//* 使用antMatchers(API)方法调用hasAnyAuthority(role)方法即可控制用户访问权限，这里的role
//* 就是集合中的字符串，在较老版本中用户是否具有权限访问某个资源取决于过滤链的“投票”返回值，
//* 为-1则无权，相反为1则可以访问
//*
//* 自定义错误页面
//* 我们可以创建一个配置类，让它实现ErrorPageRegistrar这个接口，然后创建ErrorPage，
//* 并通过HttpStatus这个枚举类传入状态码和要访问的页面的路径，最后用registry.addErrorPages
//* 方法将ErrorPage对象传入，可以传入多个该对象，因为re..的参数是可变参数
//* */
class SecurityConfig {

}