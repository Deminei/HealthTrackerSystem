package models;

import java.time.LocalDate;

public class FoodItem {
    private String name;
    private int calories;
    private LocalDate date;

    public FoodItem(String name, int calories, LocalDate date) {
        this.name = name;
        this.calories = calories;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FoodItem [name=" + name + ", calories=" + calories + ", date=" + date + "]";
    }
}
