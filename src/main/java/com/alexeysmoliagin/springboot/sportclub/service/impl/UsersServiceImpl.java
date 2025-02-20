package com.alexeysmoliagin.springboot.sportclub.service.impl;

import com.alexeysmoliagin.springboot.sportclub.exception_hundling.NoSuchUserException;
import com.alexeysmoliagin.springboot.sportclub.exception_hundling.UsersIncorrectData;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
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
        throw new NoSuchUserException("Пользователь с таким ID не найден");
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
        user.setRegisterData(LocalDateTime.now());
        user = usersRepository.save(user);
        List <Users> listFromOneUser = new ArrayList<>();
        listFromOneUser.add(user);
        List<UsersDto> result = mappingUsersToDto(listFromOneUser);
        UsersDto usersDtoResponseAfterSave = result.get(0);
        return usersDtoResponseAfterSave;
    }

    @Override
    public UsersDto updateUser(UsersDto dto, int id) {
        if (usersRepository.existsById(id)) {
            Users user = usersRepository.findById(id).get();
            user.setName(dto.getName());
            user.setSurname(dto.getSurname());
            user.setAge(dto.getAge());
            user.setGender(dto.getGender());
            user.setTelegramLogin(dto.getTelegramLogin());
            user.setPhone(dto.getPhone());
            user = usersRepository.save(user);
            List <Users> listFromOneUser = new ArrayList<>();
            listFromOneUser.add(user);
            List<UsersDto> result = mappingUsersToDto(listFromOneUser);
            UsersDto usersDtoResponseAfterSave = result.get(0);
            return usersDtoResponseAfterSave;
        }
        throw new NoSuchUserException("Пользователь с таким ID не найден");
    }

    @Override
    public String deleteUser(int id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return "Пользователь с ID = " + id + " удален";
        }
        throw new NoSuchUserException("Пользователь с таким ID не найден");
    }
}














