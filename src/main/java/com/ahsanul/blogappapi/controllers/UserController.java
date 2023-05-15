package com.ahsanul.blogappapi.controllers;


import com.ahsanul.blogappapi.models.User;
import com.ahsanul.blogappapi.payloads.UserDto;
import com.ahsanul.blogappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

//    @PostMapping("/")
//    public String createUser1(@RequestBody UserDto userDto){
//
//        return "userDto";
//    }


}
