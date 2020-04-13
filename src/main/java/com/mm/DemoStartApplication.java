package com.mm;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.mm.demo.dao")
public class DemoStartApplication {
    public static void main(String[] args){
        SpringApplication.run(DemoStartApplication.class,args);
    }
}
