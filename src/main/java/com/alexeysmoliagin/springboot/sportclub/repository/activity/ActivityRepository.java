package com.alexeysmoliagin.springboot.sportclub.repository.activity;

import com.alexeysmoliagin.springboot.sportclub.repository.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository <Activity, Integer> {
}
