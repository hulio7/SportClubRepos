package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UsersSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.alexeysmoliagin.springboot.sportclub.exceptions.ExceptionFactory.entityNotFoundException;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.UserMessage.USER_DELETE;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.ErrorsMessage.UserMessage.USER_NOT_EXIST;
import static com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory.getMessage;


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

    @Cacheable(value = "USERS", key = "#id")
    @Override
    public UsersDto getUser(int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, id));
        return usersMapper.toDto(user);
    }

    @CachePut(value = "USERS", key = "#result.id")
    @Override
    @Transactional
    public UsersDto addUser(UsersDto dto) {
        var user = usersMapper.toUsers(dto);
        user.setRegisterData(LocalDateTime.now());
        usersRepository.save(user);
        return usersMapper.toDto(user);
    }
    @CachePut(value = "USERS", key = "#result.id")
    @Override
    @Transactional
    public UsersDto updateUser(UsersDto dto, int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, id));
        var updatedUser = usersRepository.save(usersMapper.updateEntity(user, dto));
        return usersMapper.toDto(updatedUser);
    }

    @CacheEvict(value = "USERS", key = "#id")
    @Override
    @Transactional
    public String deleteUser(int id) {
        if (usersRepository.existsById(id)) {
            usersSubscriptionRepository.deleteByUserId(id);
            usersRepository.deleteById(id);
            return getMessage(USER_DELETE, id);
        }
        throw entityNotFoundException(USER_NOT_EXIST, id);
    }
}