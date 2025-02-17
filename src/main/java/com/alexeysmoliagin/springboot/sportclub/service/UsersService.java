package com.alexeysmoliagin.springboot.sportclub.service;

import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;

import java.util.List;

public interface UsersService {
    List <UsersDto> getAllUsers ();
    UsersDto getUsers (int id);
    void addUsers (UsersDto dto);
    void updateUsers (UsersDto dto, int id);
    void deleteUsers (int id);

}
