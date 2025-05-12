package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.mapper.user.UserMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.usersubscription.UserSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UserDto;
import lombok.RequiredArgsConstructor;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSubscriptionRepository userSubscriptionRepository;


    @Override
    public List<UserDto> getAllUsers() {
        var allUsers = userRepository.findAll();
        return userMapper.toUsersDtoList(allUsers);
    }

    @Override
    public UserDto getUser(int id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, id));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto dto) {
        var user = userMapper.toUsers(dto);
        user.setRegisterData(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto dto, int id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, id));
        var updatedUser = userRepository.save(userMapper.updateEntity(user, dto));
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userSubscriptionRepository.deleteByUserId(id);
            userRepository.deleteById(id);
            return getMessage(USER_DELETE, id);
        }
        throw entityNotFoundException(USER_NOT_EXIST, id);
    }
}