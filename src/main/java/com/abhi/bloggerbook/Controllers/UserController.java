package com.abhi.bloggerbook.Controllers;

import com.abhi.bloggerbook.Payloads.ApiResponse;
import com.abhi.bloggerbook.Payloads.UserDto;
import com.abhi.bloggerbook.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    //create-user

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createUserDto= this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //update-user

    @PutMapping("/{userid}")
    public  ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userid")Integer uid){

        UserDto updateUserDto= this.userService.updateUser(userDto,uid);
        return new ResponseEntity<>(updateUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userid") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<>(new ApiResponse("message: "+ "user deleted successfully",true),HttpStatus.OK);
    }

    //get- Single user
    @GetMapping("/{userid}")
    public ResponseEntity<UserDto> getAnUserById(@PathVariable("userid")Integer userid){
       UserDto userDto= this.userService.getUserById(userid);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    //get all users by id
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(),HttpStatus.OK);
    }
}
