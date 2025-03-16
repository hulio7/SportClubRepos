package com.alexeysmoliagin.springboot.sportclub.service.user;

import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UsersDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public List<UsersDto> getAllUsers() {
        var allUsers = usersRepository.findAll();
        return usersMapper.toUsersDtoList(allUsers);
    }

    @Override
    public UsersDto getUser(int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Пользователь с ID %d не найден", id)));
        return usersMapper.toDto(user);
    }

    @Override
    @Transactional
    public UsersDto addUser(UsersDto dto) {
        var user =usersMapper.toUsers(dto);
        user.setRegisterData(LocalDateTime.now());
        usersRepository.save(user);
        return usersMapper.toDto(user);
    }

    @Override
    @Transactional
    public UsersDto updateUser(UsersDto dto, int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с таким ID не найден"));
        var updatedUser = usersRepository.save(usersMapper.updateEntity(user, dto));
        return usersMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public String deleteUser(int id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return "Пользователь с ID = " + id + " удален";
        }
        throw new EntityNotFoundException(String.format("Пользователь с ID %d не найден", id));
    }
}










