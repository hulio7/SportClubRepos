package com.alexeysmoliagin.springboot.sportclub.controller.activity.model;

import lombok.Data;

@Data
public class ActivityModelAddRequest {
    private String name;
    private int minAge;
}
