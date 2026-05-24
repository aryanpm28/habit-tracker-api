package com.HabitTrackerAPI.Habit.Tracker.API;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HabitService {

    private final HabitRepository habitRepo;
    private final HabitLogRepository logRepo;

    private static final Logger logger = LoggerFactory.getLogger(HabitService.class);

    public HabitService(HabitRepository habitRepo,
            HabitLogRepository logRepo) {

        this.habitRepo = habitRepo;
        this.logRepo = logRepo;
    }

    // ================= GET ALL HABITS =================

    public List<HabitResponseDTO> getAllHabits() {

        logger.info("Fetching all habits");

        return habitRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public HabitResponseDTO getHabitById(Long id) {

        Habit habit = habitRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Habit not found"));

        return convertToDTO(habit);
    }

    // ================= ADD HABIT =================

    public HabitResponseDTO addHabit(HabitDTO dto) {

        logger.info("Adding new habit: {}", dto.getName());

        Habit habit = new Habit();

        habit.setName(dto.getName());
        habit.setTime(dto.getTime());

        // NEW FEATURES
        habit.setCategory(dto.getCategory());
        habit.setReminderEnabled(dto.isReminderEnabled());

        habit.setCreatedDate(LocalDate.now());

        Habit savedHabit = habitRepo.save(habit);

        logger.info("Habit created successfully");

        return convertToDTO(savedHabit);
    }

    // ================= MARK COMPLETED =================

    public String markCompleted(Long habitId) {

        Habit habit = habitRepo.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        if (logRepo.existsByHabitIdAndDate(
                habitId,
                LocalDate.now())) {

            logger.warn("Habit already completed today");

            throw new HabitAlreadyCompletedException(
                    "Habit already marked today");
        }

        HabitLog log = new HabitLog();

        log.setHabit(habit);
        log.setDate(LocalDate.now());
        log.setCompleted(true);

        HabitLog savedLog = logRepo.save(log);

        logger.info("Saved Log ID: {}", savedLog.getId());

        habit.setCompletedDate(LocalDate.now());

        habitRepo.save(habit);

        logger.info("Habit marked completed");

        return "Habit marked completed successfully";
    }
    // ================= STREAK =================

    public int getStreak(Long habitId) {

        List<HabitLog> logs = logRepo.findByHabitIdOrderByDateDesc(habitId);

        int streak = 0;

        LocalDate today = LocalDate.now();

        for (HabitLog log : logs) {

            if (log.isCompleted() &&
                    log.getDate().equals(today.minusDays(streak))) {

                streak++;

            } else {
                break;
            }
        }

        logger.info("Current streak: {}", streak);

        return streak;
    }

    // ================= HISTORY =================

    public List<LogDTO> getHistory(Long habitId) {

        logger.info("Fetching habit history");

        return logRepo.findByHabitIdOrderByDateDesc(habitId)
                .stream()
                .map(this::convertLogToDTO)
                .toList();
    }

    // ================= CHECK COMPLETED TODAY =================

    public boolean isCompletedToday(Long habitId) {

        return logRepo.existsByHabitIdAndDate(
                habitId,
                LocalDate.now());
    }

    // ================= DELETE HABIT =================

    public String deleteHabit(Long id) {

        if (!habitRepo.existsById(id)) {

            logger.error("Habit not found");

            throw new ResourceNotFoundException(
                    "Habit not found");
        }

        habitRepo.deleteById(id);

        logger.info("Habit deleted successfully");

        return "Habit deleted successfully";
    }

    public HabitResponseDTO updateHabit(
            Long id,
            HabitDTO dto) {

        Habit habit = habitRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Habit not found"));

        habit.setName(dto.getName());
        habit.setDescription(dto.getDescription());
        habit.setCategory(dto.getCategory());
        habit.setReminderEnabled(
                dto.isReminderEnabled());

        habit.setTime(dto.getTime());

        Habit updatedHabit = habitRepo.save(habit);

        logger.info("Habit updated successfully");

        return convertToDTO(updatedHabit);
    }

    // ================= STATISTICS =================

    public HabitStatsDTO getStatistics() {

        int totalHabits = habitRepo.findAll().size();

        long completedToday = habitRepo.findAll()
                .stream()
                .filter(habit -> isCompletedToday(habit.getId()))
                .count();

        HabitStatsDTO stats = new HabitStatsDTO();

        stats.setTotalHabits(totalHabits);
        stats.setCompletedToday((int) completedToday);

        return stats;
    }

    // ================= DTO CONVERSION =================

    private HabitResponseDTO convertToDTO(Habit habit) {

        HabitResponseDTO dto = new HabitResponseDTO();

        dto.setId(habit.getId());
        dto.setName(habit.getName());
        dto.setTime(habit.getTime());

        // NEW FIELDS
        dto.setCategory(habit.getCategory());
        dto.setReminderEnabled(habit.isReminderEnabled());

        dto.setCompletedToday(
                isCompletedToday(habit.getId()));

        dto.setStreak(
                getStreak(habit.getId()));

        if (habit.getLogs() != null) {

            dto.setLogs(
                    habit.getLogs()
                            .stream()
                            .map(this::convertLogToDTO)
                            .toList());
        }

        return dto;
    }

    private LogDTO convertLogToDTO(HabitLog log) {

        LogDTO dto = new LogDTO();

        dto.setId(log.getId());
        dto.setCompleted(log.isCompleted());
        dto.setDate(log.getDate());

        return dto;
    }
}