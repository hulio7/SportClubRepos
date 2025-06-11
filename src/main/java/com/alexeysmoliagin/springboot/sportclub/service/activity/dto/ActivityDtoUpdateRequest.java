package com.alexeysmoliagin.springboot.sportclub.service.activity.dto;

import lombok.Data;

@Data
public class ActivityDtoUpdateRequest {
    private String name;
    private int minAge;
}
