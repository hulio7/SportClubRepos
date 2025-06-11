package com.alexeysmoliagin.springboot.sportclub.service.activity.dto;

import lombok.Data;

@Data
public class ActivityDtoGetResponse {
    private int id;
    private String name;
    private int minAge;
}
