package com.abhi.bloggerbook.Services.impel;

import com.abhi.bloggerbook.Exceptions.ResourceNotFoundException;
import com.abhi.bloggerbook.Payloads.UserDto;
import com.abhi.bloggerbook.Repositories.UserRepo;
import com.abhi.bloggerbook.Services.UserService;
import com.abhi.bloggerbook.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

   @Autowired
   ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
        User savedUser=this.userRepo.save(userDtoToEntity(user));
        return this.entityToUserDto(savedUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser= this.userRepo.save(user);
        return entityToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return entityToUserDto(user);
    }



    @Override
    public List<UserDto> getAllUsers() {

        List<User> allUsers= this.userRepo.findAll();
        List<UserDto> allUsersDto= new ArrayList<>();
        for(User u: allUsers)
        {
            allUsersDto.add(entityToUserDto(u));
        }
        return allUsersDto;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);
    }

    private User userDtoToEntity(UserDto userDto)
    {
       User user= this.modelMapper.map(userDto,User.class);
       /*user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setId(userDto.getId());
       user.setPassword(userDto.getPassword());
       user.setAbout(userDto.getAbout());*/

       return user;
    }

    private UserDto entityToUserDto(User user)
    {

        UserDto userDto= this.modelMapper.map(user,UserDto.class);
        /*userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());*/

        return userDto;
    }
}
