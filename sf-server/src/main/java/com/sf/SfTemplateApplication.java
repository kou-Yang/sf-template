package com.sf;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author kouyang
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.sf")
public class SfTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfTemplateApplication.class, args);
    }

}
