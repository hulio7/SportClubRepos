package com.alexeysmoliagin.springboot.sportclub.controller.schedule.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleResponseModel {

    private int id;

    private String name;

    private Integer activityId;

    private Integer coachId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
