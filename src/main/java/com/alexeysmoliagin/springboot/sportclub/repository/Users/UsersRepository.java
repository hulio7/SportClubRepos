package com.alexeysmoliagin.springboot.sportclub.repository.Users;

import com.alexeysmoliagin.springboot.sportclub.repository.Users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users, Integer> {
}
