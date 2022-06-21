package com.abhi.bloggerbook.Payloads;

import com.abhi.bloggerbook.models.Category;
import com.abhi.bloggerbook.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {


    private String postId;

    @NotNull
    @NotEmpty
    private String title;

    private String content;

//    private String imageName;
//
//    private Date addedDate;
//
//
    private CategoryDto category;
//
//
    private UserDto user;
}
