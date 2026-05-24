package com.HabitTrackerAPI.Habit.Tracker.API;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// DTO is used to transfer only required data
@Data
public class HabitDTO {

    @NotBlank(message = "Habit name is required")
    @Size(min = 3, max = 50)
    private String name;

    private String description;

    private String category;

    private boolean reminderEnabled;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
}