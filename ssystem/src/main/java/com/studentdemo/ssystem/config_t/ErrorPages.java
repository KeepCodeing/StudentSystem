package com.studentdemo.ssystem.config_t;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

// @Configuration
public class ErrorPages implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        // 定义404状态码的页面
        ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        registry.addErrorPages(errorPage);
    }
}
