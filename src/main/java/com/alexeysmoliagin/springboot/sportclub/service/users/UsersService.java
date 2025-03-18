package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDto;

import java.util.List;

public interface UsersService {
    List <UsersDto> getAllUsers ();
    UsersDto getUser (int id);
    UsersDto addUser (UsersDto dto);
    UsersDto updateUser (UsersDto dto, int id);
    String deleteUser (int id);
}
