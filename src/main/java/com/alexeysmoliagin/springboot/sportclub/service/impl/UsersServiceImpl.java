package com.alexeysmoliagin.springboot.sportclub.service.impl;

import com.alexeysmoliagin.springboot.sportclub.exception_hundling.NoSuchUserException;
import com.alexeysmoliagin.springboot.sportclub.mapper.usersMapper.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.UsersService;
import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public List<UsersDto> getAllUsers() {
        List <Users> allUsers = usersRepository.findAll();
        return usersMapper.toUsersDtoList(allUsers);
    }

    @Override
    public UsersDto getUser(int id) {
        Users user = null;
        Optional<Users> optional = usersRepository.findById(id);
        if(optional.isPresent()) {
            user = optional.get();
            return usersMapper.toDto(user);
        }
        throw new NoSuchUserException("Пользователь с таким ID не найден");
    }

    @Override
    @Transactional
    public UsersDto addUser(UsersDto dto) {
        Users user = usersRepository.save(usersMapper.toUsers(dto));
        return usersMapper.toDto(user);
    }

    @Override
    @Transactional
    public UsersDto updateUser(UsersDto dto, int id) {
        if (usersRepository.existsById(id)) {
            Users user = usersRepository.save(usersMapper.toUsers(dto));
            return usersMapper.toDto(user) ;
        }
        throw new NoSuchUserException("Пользователь с таким ID не найден");
    }

    @Override
    @Transactional
    public String deleteUser(int id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return "Пользователь с ID = " + id + " удален";
        }
        throw new NoSuchUserException("Пользователь с таким ID не найден");
    }
}














