package com.abhi.bloggerbook.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cat_id")
    private int categoryId;

    @Column( name="title")
    private String categoryTitle;

    @Column( name="description")
    private String categoryDesc;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Post> posts= new ArrayList<>();
}
