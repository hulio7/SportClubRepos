package com.alexeysmoliagin.springboot.sportclub.repository.users;

import com.alexeysmoliagin.springboot.sportclub.repository.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users, Integer> {
}
