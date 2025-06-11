package com.alexeysmoliagin.springboot.sportclub.service.schedule.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDtoAddRequest {

    private String name;

    private Integer activityId;

    private Integer coachId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
