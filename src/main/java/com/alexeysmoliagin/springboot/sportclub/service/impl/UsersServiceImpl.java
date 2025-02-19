package com.alexeysmoliagin.springboot.sportclub.service.impl;

import com.alexeysmoliagin.springboot.sportclub.repository.Users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

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
            dto.setRegisterData(user.getRegisterData());
            result.add(dto);
        }
        return result;
    }

    @Override
    public UsersDto getUser(int id) {
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
            dto.setRegisterData(user.getRegisterData());
            return dto;
        }
        throw new RuntimeException("Пользователь с таким ID не найден");
    }

    @Override
    public UsersDto addUser(UsersDto dto) {
        Users user = new Users();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setTelegramLogin(dto.getTelegramLogin());
        user.setPhone(dto.getPhone());
        user.setRegisterData(dto.getRegisterData());
        usersRepository.save(user);
//        UsersDto usersDtoResponse = new UsersDto();
//        usersDtoResponse.setId(user.getId());
//        usersDtoResponse.setName(user.getName());
//        usersDtoResponse.setSurname(user.getSurname());
//        usersDtoResponse.setGender(user.getGender());
//        usersDtoResponse.setPhone(user.getPhone());
//        usersDtoResponse.setTelegramLogin(user.getTelegramLogin());
//        usersDtoResponse.setAge(user.getAge());
//        usersDtoResponse.setRegisterData(user.getRegisterData());
        UsersDto usersDtoResponseAfterSave = getUser(user.getId());
        return usersDtoResponseAfterSave;
    }

    @Override
    public UsersDto updateUser(UsersDto dto, int id) {
        if (usersRepository.existsById(id)) {
            Users user = new Users();
            user.setName(dto.getName());
            user.setSurname(dto.getSurname());
            user.setAge(dto.getAge());
            user.setGender(dto.getGender());
            user.setTelegramLogin(dto.getTelegramLogin());
            user.setPhone(dto.getPhone());
            usersRepository.save(user);
            UsersDto usersDtoResponseAfterSave = getUser(user.getId());
            return usersDtoResponseAfterSave;
        }
        throw new RuntimeException("Пользователь с таким ID не найден");
    }

    @Override
    public String deleteUser(int id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return "Пользователь с ID = " + id + " удален";
        }
        throw new RuntimeException("Пользователь с таким ID не найден");
    }
}














