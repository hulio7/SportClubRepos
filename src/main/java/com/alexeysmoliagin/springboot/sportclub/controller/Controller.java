package com.alexeysmoliagin.springboot.sportclub.controller;

import com.alexeysmoliagin.springboot.sportclub.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<Users> showAllUsers() {
        List<Users> allUsers = usersService.getAllUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public Users getUser(@PathVariable int id) {
        Users user = usersService.getUser(id);
        return user;
    }

    @PostMapping("/users")
    public Users addNewUser(@RequestBody Users user) {
        usersService.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    public Users updateUser(@RequestBody Users user) {
        usersService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users")
    public void deleteUser(@PathVariable int id) {
        usersService.deleteUser(id);
    }
}








