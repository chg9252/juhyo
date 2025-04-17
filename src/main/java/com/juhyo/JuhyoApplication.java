package com.juhyo;

import com.juhyo.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class JuhyoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuhyoApplication.class, args);
    }

}
