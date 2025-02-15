package com.alexeysmoliagin.springboot.sportclub.service.user.impl;

import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.service.user.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> allUsers = usersRepository.findAll();
        return mappingToDto(allUsers);
    }

    @Override
    public void saveUser(UsersDto dto) {
        Users user = new Users();
        user.setId(dto.getId());
        user.setGender(dto.getGender());
        user.setAge(dto.getAge());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhone(dto.getPhone());
        user.setTelegramLogin(dto.getTelegramLogin());
        user.setRegisterDate(LocalDateTime.now());
        usersRepository.save(user);
    }

    @Override
    public UsersDto getUser(int id) {
        Users user = null;
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
            UsersDto dto = new UsersDto();
            dto.setId(user.getId());
            dto.setAge(user.getAge());
            dto.setGender(user.getGender());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setTelegramLogin(user.getTelegramLogin());
            dto.setRegisterDate(user.getRegisterDate());
            return dto;
        }
        throw new RuntimeException("Пользователь по id не найден");
    }

    @Override
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public void updateUser(UsersDto dto, int id) {
        boolean isExists = usersRepository.existsById(id);
        if (isExists){
            Users user = usersRepository.findById(id).get();
            user.setGender(dto.getGender());
            user.setAge(dto.getAge());
            user.setName(dto.getName());
            user.setSurname(dto.getSurname());
            user.setPhone(dto.getPhone());
            user.setTelegramLogin(dto.getTelegramLogin());
            user.setRegisterDate(LocalDateTime.now());
            usersRepository.save(user);
        } else {
            throw new RuntimeException("Пользователя не существует");
        }
    }

    private List<UsersDto> mappingToDto(List<Users> allUsers) {
        List<UsersDto> result = new ArrayList<>();
        for (Users user : allUsers){
            UsersDto dto = new UsersDto();
            dto.setId(user.getId());
            dto.setAge(user.getAge());
            dto.setGender(user.getGender());
            dto.setName(user.getName());
            dto.setSurname(user.getSurname());
            dto.setTelegramLogin(user.getTelegramLogin());
            dto.setRegisterDate(user.getRegisterDate());
            result.add(dto);
        }
        return result;
    }
}












