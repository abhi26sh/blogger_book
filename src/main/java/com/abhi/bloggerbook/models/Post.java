package com.abhi.bloggerbook.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @NotNull
    private String title;

    @Column(length = 1000,nullable = false)
    private String content;


    private String imageName;

    @Column(name="curr_date")
    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}
