package com.unsw.waitsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.unsw.waitsystem.mapper")
public class WaitsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaitsystemApplication.class, args);
    }

}
