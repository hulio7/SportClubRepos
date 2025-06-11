package com.alexeysmoliagin.springboot.sportclub.repository.schedule;

import com.alexeysmoliagin.springboot.sportclub.repository.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
