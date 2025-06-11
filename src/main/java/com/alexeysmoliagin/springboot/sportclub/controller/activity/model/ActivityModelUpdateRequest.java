package com.alexeysmoliagin.springboot.sportclub.controller.activity.model;

import lombok.Data;

@Data
public class ActivityModelUpdateRequest {
    private String name;
    private int minAge;
}
