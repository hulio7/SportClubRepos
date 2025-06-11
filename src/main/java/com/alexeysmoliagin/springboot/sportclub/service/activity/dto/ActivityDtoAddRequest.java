package com.alexeysmoliagin.springboot.sportclub.service.activity.dto;

import lombok.Data;

@Data
public class ActivityDtoAddRequest {
    private String name;
    private int minAge;
}
