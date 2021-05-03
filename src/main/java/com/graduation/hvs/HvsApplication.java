package com.graduation.hvs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.graduation.hvs.mapper")
public class HvsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HvsApplication.class, args);
    }

}
