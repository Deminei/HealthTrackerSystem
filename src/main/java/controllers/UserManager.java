package controllers;

import models.Exercise;
import models.FoodItem;
import models.SleepRecord;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users; // holds all users in the system
    private User currentUser; // holds the user currently logged in
    private FileManager fileManager;

    public UserManager() {
        this.users = new ArrayList<>();
        this.currentUser = null;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void createUser(String username) {
        User newUser = new User(username);
        this.users.add(newUser);
        this.currentUser = newUser; // Set the new user as the current user
        if (fileManager != null) {
            try {
                fileManager.saveData(); // Save the new user's data
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean login(String username) {
        User user = findUser(username);
        if (user != null) {
            this.currentUser = user;
            return true;
        } else {
            return false;
        }
    }

    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            System.out.println("Added user: " + user.getUsername());
            System.out.println("Total users: " + users.size());
        }
    }

    public void addFoodItem(FoodItem foodItem) {
        this.currentUser.addFoodItem(foodItem);
    }

    public void addExercise(Exercise exercise) {
        this.currentUser.addExercise(exercise);
    }

    public void addSleepRecord(SleepRecord sleepRecord) {
        this.currentUser.addSleepRecord(sleepRecord);
    }

}

