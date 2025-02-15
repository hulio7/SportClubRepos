package com.alexeysmoliagin.springboot.sportclub.repository;

import com.alexeysmoliagin.springboot.sportclub.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Integer> {
}
