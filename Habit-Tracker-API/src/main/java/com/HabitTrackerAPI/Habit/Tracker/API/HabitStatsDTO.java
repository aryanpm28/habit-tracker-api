package com.HabitTrackerAPI.Habit.Tracker.API;

import lombok.Data;

@Data
public class HabitStatsDTO {

    private int totalHabits;
    
    private int completedToday;

}