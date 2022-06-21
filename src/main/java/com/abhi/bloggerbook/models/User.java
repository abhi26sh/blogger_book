package com.abhi.bloggerbook.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="user_name",nullable = false,length=100)
    private String name;
    @Column(name="user_mail")
    private String email;
    @Column(name="user_password")
    private String password;
    @Column(name="about",length = 300)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Post> posts= new ArrayList<>();
}
