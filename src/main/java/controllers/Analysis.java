package controllers;

import models.Exercise;
import models.FoodItem;
import models.SleepRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    public static double calculateAverageCalories(List<FoodItem> foodItems) {
        if (foodItems.size() == 0) return 0;

        int totalCalories = 0;
        for (FoodItem foodItem : foodItems) {
            totalCalories += foodItem.getCalories();
        }

        return (double) totalCalories / foodItems.size();
    }

    public static double calculateTotalExerciseDuration(List<Exercise> exercises) {
        int totalDuration = 0;
        for (Exercise exercise : exercises) {
            totalDuration += exercise.getDuration();
        }

        return totalDuration;
    }

    public static double calculateAverageSleepHours(List<SleepRecord> sleepRecords) {
        if (sleepRecords.size() == 0) return 0;

        int totalSleepHours = 0;
        for (SleepRecord sleepRecord : sleepRecords) {
            totalSleepHours += sleepRecord.calculateSleepHours();
        }

        return (double) totalSleepHours / sleepRecords.size();
    }

    private static List<FoodItem> getItemsWithinDateRange(List<FoodItem> foodItems, LocalDate startDate, LocalDate endDate) {
        List<FoodItem> itemsWithinRange = new ArrayList<>();

        for (FoodItem foodItem : foodItems) {
            LocalDate date = foodItem.getDate();
            if (date.isAfter(startDate) || date.isEqual(startDate)) {
                if (date.isBefore(endDate) || date.isEqual(endDate)) {
                    itemsWithinRange.add(foodItem);
                }
            }
        }

        return itemsWithinRange;
    }

    private static List<Exercise> getExercisesWithinDateRange(List<Exercise> exercises, LocalDate startDate, LocalDate endDate) {
        List<Exercise> exercisesWithinRange = new ArrayList<>();

        for (Exercise exercise : exercises) {
            LocalDate date = exercise.getDate();
            if (date.isAfter(startDate) || date.isEqual(startDate)) {
                if (date.isBefore(endDate) || date.isEqual(endDate)) {
                    exercisesWithinRange.add(exercise);
                }
            }
        }

        return exercisesWithinRange;
    }

    private static List<SleepRecord> getSleepRecordsWithinDateRange(List<SleepRecord> sleepRecords, LocalDate startDate, LocalDate endDate) {
        List<SleepRecord> sleepRecordsWithinRange = new ArrayList<>();

        for (SleepRecord sleepRecord : sleepRecords) {
            LocalDate date = sleepRecord.getDate();
            if (date.isAfter(startDate) || date.isEqual(startDate)) {
                if (date.isBefore(endDate) || date.isEqual(endDate)) {
                    sleepRecordsWithinRange.add(sleepRecord);
                }
            }
        }

        return sleepRecordsWithinRange;
    }
}

