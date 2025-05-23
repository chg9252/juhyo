package com.jeoyo;

import com.jeoyo.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class JeoyoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeoyoApplication.class, args);
    }

}
