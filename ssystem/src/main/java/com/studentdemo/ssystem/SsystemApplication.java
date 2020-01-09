package com.studentdemo.ssystem;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
@MapperScan("com.studentdemo.ssystem.DAO")
public class SsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsystemApplication.class, args);
    }

}
