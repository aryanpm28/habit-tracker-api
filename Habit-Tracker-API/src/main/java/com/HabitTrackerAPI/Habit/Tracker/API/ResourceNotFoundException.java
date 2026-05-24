package com.HabitTrackerAPI.Habit.Tracker.API;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}