package com.alexeysmoliagin.springboot.sportclub.repository.user;

import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Integer> {
}
