package com.example.springboottwoapps.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {

    private int id;
    private String title;

    private String description;

    private LocalDateTime createdAt;

}
