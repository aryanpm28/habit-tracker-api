package com.HabitTrackerAPI.Habit.Tracker.API;

public class HabitAlreadyCompletedException extends RuntimeException {

    public HabitAlreadyCompletedException(String message) {
        super(message);
    }
}