package com.alexeysmoliagin.springboot.sportclub.controller.schedule.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleUpdateModel {

    private String name;

    private Integer activityId;

    private Integer coachId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
