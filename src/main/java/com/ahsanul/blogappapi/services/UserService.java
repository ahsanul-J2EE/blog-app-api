package com.ahsanul.blogappapi.services;

import com.ahsanul.blogappapi.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer id);
    UserDto getUserById(Integer id);

    List<UserDto> getAllUser();

    void userDelete (Integer userId);

}
