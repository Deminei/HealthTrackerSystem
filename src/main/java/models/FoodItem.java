package models;

import java.time.LocalDate;

public class FoodItem {
    private String name;
    private int calories;
    private LocalDate date;

    //constructor
    public FoodItem(String name, int calories, LocalDate date) {
        this.name = name;
        this.calories = calories;
        this.date = date;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FoodItem [name=" + name + ", calories=" + calories + ", date=" + date + "]";
    }



// constructor, getters, setters...
}
