package com.alexeysmoliagin.springboot.sportclub.repository.user;

import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {
}
