package com.alexeysmoliagin.springboot.sportclub.service.users;

import com.alexeysmoliagin.springboot.sportclub.exceptions.NoSuchEntityException;
import com.alexeysmoliagin.springboot.sportclub.mapper.users.UsersMapper;
import com.alexeysmoliagin.springboot.sportclub.messageSource.MessageSourceFactory;
import com.alexeysmoliagin.springboot.sportclub.repository.users.UsersRepository;
import com.alexeysmoliagin.springboot.sportclub.repository.userssubscription.UsersSubscriptionRepository;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public UsersDto getUser(int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException(getMessage(USER_NOT_EXIST, id)));
        return usersMapper.toDto(user);
    }

    @Override
    @Transactional
    public UsersDto addUser(UsersDto dto) {
        var user = usersMapper.toUsers(dto);
        user.setRegisterData(LocalDateTime.now());
        usersRepository.save(user);
        return usersMapper.toDto(user);
    }

    @Override
    @Transactional
    public UsersDto updateUser(UsersDto dto, int id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException(getMessage(USER_NOT_EXIST, id)));
        var updatedUser = usersRepository.save(usersMapper.updateEntity(user, dto));
        return usersMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public String deleteUser(int id) {
        if (usersRepository.existsById(id)) {
            usersSubscriptionRepository.deleteByUserId(id);
            usersRepository.deleteById(id);
            return getMessage(USER_DELETE, id);
        }
        throw new NoSuchEntityException(getMessage(USER_NOT_EXIST, id));
    }
}