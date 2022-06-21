package com.abhi.bloggerbook.Services;

import com.abhi.bloggerbook.Payloads.UserDto;
import com.abhi.bloggerbook.models.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
