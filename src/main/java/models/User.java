package models;

import controllers.FileManager;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<FoodItem> foodItems;
    private List<Exercise> exerciseActivities;
    private List<SleepRecord> sleepRecords;

    //constructor
    public User(String username) {
        this.username = username;
        this.foodItems = new ArrayList<>();
        this.exerciseActivities = new ArrayList<>();
        this.sleepRecords = new ArrayList<>();
         // Assign the fileManager instance
    }


    public String getUsername() {
        return this.username;
    }

    public List<FoodItem> getFoodItems() {
        return this.foodItems;
    }

    public List<Exercise> getExerciseActivities() {
        return this.exerciseActivities;
    }

    public List<SleepRecord> getSleepRecords() {
        return this.sleepRecords;
    }

    // setter methods
    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public void setExerciseActivities(List<Exercise> exerciseActivities) {
        this.exerciseActivities = exerciseActivities;
    }

    public void setSleepRecords(List<SleepRecord> sleepRecords) {
        this.sleepRecords = sleepRecords;
    }

    public void addFoodItem(FoodItem foodItem) {
        if (!foodItems.contains(foodItem)) {
            foodItems.add(foodItem);
        }
    }


    public void addExercise(Exercise exercise) {
        if (!exerciseActivities.contains(exercise)) {
            exerciseActivities.add(exercise);
        }
    }


    public void addSleepRecord(SleepRecord sleepRecord) {
        if (!sleepRecords.contains(sleepRecord)) {
            sleepRecords.add(sleepRecord);
        }
    }


    @Override
    public String toString() {
        return "User [username=" + username + "]";
    }
}
