package models;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;

public class SleepRecord {
    private LocalDate date;
    private LocalTime sleepTime;
    private LocalTime wakeupTime;

    //constructor
    public SleepRecord(LocalTime sleepTime, LocalTime wakeupTime, LocalDate date) {
        this.sleepTime = sleepTime;
        this.wakeupTime = wakeupTime;
        this.date = date;
    }

    //getters, setters...
    public LocalDate getDate() {
        return date;
    }


    public LocalTime getSleepTime() {
        return sleepTime;
    }

    public LocalTime getWakeupTime() {
        return wakeupTime;
    }

    public int calculateSleepHours() {
        Duration duration = Duration.between(sleepTime, wakeupTime);
        return (int) duration.toHours();
    }
}
