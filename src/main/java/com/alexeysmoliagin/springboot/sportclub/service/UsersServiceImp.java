package com.alexeysmoliagin.springboot.sportclub.service;

import com.alexeysmoliagin.springboot.sportclub.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void saveUser(Users user) {
        usersRepository.save(user);
    }

    @Override
    public Users getUser(int id) {
        Users user = null;
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get ();
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }
}












