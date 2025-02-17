package com.alexeysmoliagin.springboot.sportclub.service.impl;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersResponseModel;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersDtoImpl implements UsersService {

    UsersRepository usersRepository;
    @Override
    public List<UsersDto> getAllUsers() {
        List <Users> allUsers = usersRepository.findAll();
        return mappingUsersToDto (allUsers);
    }

    private List<UsersDto> mappingUsersToDto(List<Users> allUsers) {
        List<UsersDto> result = new ArrayList<>();
        for (Users user : allUsers) {
            UsersDto dto = new UsersDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setGender(user.getGender());
            dto.setPhone(user.getPhone());
            dto.setTelegramLogin(user.getTelegramLogin());
            dto.setAge(user.getAge());
            result.add(dto);
        }
        return result;
    }

    @Override
    public UsersDto getUsers(int id) {
        Users user = null;
        Optional<Users> optional = usersRepository.findById(id);
        if(optional.isPresent()) {
            user = optional.get();
            UsersDto dto = new UsersDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setGender(user.getGender());
            dto.setPhone(user.getPhone());
            dto.setTelegramLogin(user.getTelegramLogin());
            dto.setAge(user.getAge());
            return dto;
        }
        throw new RuntimeException("Пользователь с таким ID не найден");
    }

    @Override
    public void addUsers(UsersDto dto) {
        Users user = new Users();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setTelegramLogin(dto.getTelegramLogin());
        user.setId(dto.getId());
        user.setPhone(dto.getPhone());
        user.setRegisterData(dto.getRegisterData());
        usersRepository.save(user);

    }

    @Override
    public void updateUsers(UsersDto dto, int id) {
        Users user = new Users();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setTelegramLogin(dto.getTelegramLogin());
        user.setId(dto.getId());
        user.setPhone(dto.getPhone());
        user.setRegisterData(dto.getRegisterData());
        usersRepository.save(user);
    }

    @Override
    public void deleteUsers(int id) {
        usersRepository.deleteById(id);

    }
}
