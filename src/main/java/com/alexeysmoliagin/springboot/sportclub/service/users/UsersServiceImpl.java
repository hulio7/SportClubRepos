package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.exceptions.NoSuchEntityException;
import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.infrastructure.output.KafkaProducer;
import com.alexeysmoliagin.springboot.sportclub.repository.users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UsersSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDto;
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
    private final UsersSubscriptionRepository usersSubscriptionRepository;

    @Override
    public List<UsersDto> getAllUsers() {
        var allUsers = usersRepository.findAll();
        return usersMapper.toUsersDtoList(allUsers);
    }

    @Override
    public UsersDto getUser(int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException(String.format("Пользователь с ID %d не найден", id)));
        return usersMapper.toDto(user);
    }

    @Override
    @Transactional
    public UsersDto addUser(UsersDto dto) {
        var user = usersMapper.toUsers(dto);
        user.setRegisterData(LocalDateTime.now());
        usersRepository.save(user);
//        kafkaProducer.sendMessage(user.getName() + " " + user.getSurname() +
//                " , поздравляем с приобретением абонемента и вступлением в наш клуб!", "topic-1");
        return usersMapper.toDto(user);
    }

    @Override
    @Transactional
    public UsersDto updateUser(UsersDto dto, int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException(String.format("Пользователь с ID %d не найден", id)));
        var updatedUser = usersRepository.save(usersMapper.updateEntity(user, dto));
        return usersMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public String deleteUser(int id) {
        if (usersRepository.existsById(id)) {
            usersSubscriptionRepository.deleteByUserId(id);
            usersRepository.deleteById(id);
            return String.format("Пользователь с ID %d удален", id);
        }
        throw new NoSuchEntityException(String.format("Пользователь с ID %d не найден", id));
    }

}














