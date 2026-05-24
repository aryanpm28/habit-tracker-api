package com.HabitTrackerAPI.Habit.Tracker.API;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Entity class represents Habit table in database
// Each object is stored as a row
@Entity
@Data
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the habit
    @NotBlank(message = "Habit name cannot be empty")
    @Size(min = 3, max = 50)
    private String name;

    private String description;

    private String category;

    private boolean reminderEnabled;

    private int streak;

    private LocalDate createdDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    @JsonManagedReference
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitLog> logs;

    private LocalDate completedDate;

}
