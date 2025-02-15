package com.alexeysmoliagin.springboot.sportclub.service.user;

import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UsersDto;

import java.util.List;

public interface UsersService {

    public List<UsersDto> getAllUsers ();
    public void saveUser (UsersDto dto);
    public UsersDto getUser (int id);
    public void deleteUser (int id);
            void updateUser(UsersDto dto, int id);
}
