package controllers;

import models.Exercise;
import models.FoodItem;
import models.SleepRecord;
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
}