package com.alexeysmoliagin.springboot.sportclub.service;

import com.alexeysmoliagin.springboot.sportclub.entity.Users;

import java.util.List;

public interface UsersService {

    public List<Users> getAllUsers ();
    public void saveUser (Users user);
    public Users getUser (int id);
    public void deleteUser (int id);

}
