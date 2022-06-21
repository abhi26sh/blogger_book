package com.abhi.bloggerbook.config;

import com.abhi.bloggerbook.Services.PostService;
import com.abhi.bloggerbook.Services.impel.PostServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }


    @Bean
    public PostService postService()
    {
        PostService postService= new PostServiceImp();
        return postService;
    }
}
