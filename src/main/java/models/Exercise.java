package models;

import java.time.LocalDate;

public class Exercise {
    private String type;
    private int duration;
    private int caloriesBurned;
    private LocalDate date;

    public Exercise(String type, int duration, int caloriesBurned, LocalDate date) {
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Exercise [type=" + type + ", duration=" + duration + ", caloriesBurned=" + caloriesBurned + ", date=" + date + "]";
    }
}