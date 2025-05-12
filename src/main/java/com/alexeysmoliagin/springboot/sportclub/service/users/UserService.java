package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UserDto;

import java.util.List;

public interface UserService {
    List <UserDto> getAllUsers ();
    UserDto getUser (int id);
    UserDto addUser (UserDto dto);
    UserDto updateUser (UserDto dto, int id);
    String deleteUser (int id);
}
