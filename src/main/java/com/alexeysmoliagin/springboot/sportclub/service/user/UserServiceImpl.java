package com.alexeysmoliagin.springboot.sportclub.service.user;

import com.alexeysmoliagin.springboot.sportclub.mapper.user.UserMapper;
import com.alexeysmoliagin.springboot.sportclub.repository.user.UserRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.userSubscription.UserSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoAddResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoGetResponse;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateRequest;
import com.alexeysmoliagin.springboot.sportclub.service.user.dto.UserDtoUpdateResponse;
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
    public List<UserDtoGetResponse> getAllUsers() {
        var allUsers = userRepository.findAll();
        return userMapper.toUserDtoList(allUsers);
    }

//    @Cacheable(value = "USERS", key = "#id")
    @Override
    public UserDtoGetResponse getUser(int id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, id));
        return userMapper.toUserDtoGet(user);
    }

//    @CachePut(value = "USERS", key = "#result.id")
    @Override
    @Transactional
    public UserDtoAddResponse addUser(UserDtoAddRequest dto) {
        var user = userMapper.toUser(dto);
        user.setRegisterData(LocalDateTime.now());
        return userMapper.toUserDtoAdd(userRepository.save(user));
    }
//    @CachePut(value = "USERS", key = "#result.id")
    @Override
    @Transactional
    public UserDtoUpdateResponse updateUser(UserDtoUpdateRequest dto, int id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> entityNotFoundException(USER_NOT_EXIST, id));
        var updatedUser = userRepository.save(userMapper.updateEntity(user, dto));
        return userMapper.toUserDtoUpdateResponse(updatedUser);
    }

//    @CacheEvict(value = "USERS", key = "#id")
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