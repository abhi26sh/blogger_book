package com.abhi.bloggerbook;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BloggerBookApplication {

    public static void main(String[] args) {

        SpringApplication.run(BloggerBookApplication.class, args);

    }

}
