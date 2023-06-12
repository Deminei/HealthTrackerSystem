package controllers;

import models.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private UserManager userManager;


    public FileManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void saveData() throws IOException {
        try {
            // Get the existing data from the files
            List<String> existingFoodData = readDataFromFile("food.txt");
            List<String> existingExerciseData = readDataFromFile("exercise.txt");
            List<String> existingSleepData = readDataFromFile("sleep.txt");

            // Write the users and their health data to separate files
            FileWriter userWriter = new FileWriter("users.txt");
            FileWriter foodWriter = new FileWriter("food.txt", true);
            FileWriter exerciseWriter = new FileWriter("exercise.txt", true);
            FileWriter sleepWriter = new FileWriter("sleep.txt", true);

            for (User user : userManager.getUsers()) {
                // Save the user to the users file
                userWriter.write(user.getUsername() + "\n");

                // Save food data
                for (FoodItem food : user.getFoodItems()) {
                    String foodData = user.getUsername() + "," + food.getName() + "," + food.getCalories() + "," + food.getDate();
                    if (!existingFoodData.contains(foodData)) {
                        foodWriter.write(foodData + "\n");
                        existingFoodData.add(foodData);
                    }
                }

                // Save exercise data
                for (Exercise exercise : user.getExerciseActivities()) {
                    String exerciseData = user.getUsername() + "," + exercise.getType() + "," + exercise.getDuration() + "," + exercise.getCaloriesBurned() + "," + exercise.getDate();
                    if (!existingExerciseData.contains(exerciseData)) {
                        exerciseWriter.write(exerciseData + "\n");
                        existingExerciseData.add(exerciseData);
                    }
                }

                // Save sleep data
                for (SleepRecord sleep : user.getSleepRecords()) {
                    String sleepData = user.getUsername() + "," + sleep.getSleepTime() + "," + sleep.getWakeupTime() + "," + sleep.getDate();
                    if (!existingSleepData.contains(sleepData)) {
                        sleepWriter.write(sleepData + "\n");
                        existingSleepData.add(sleepData);
                    }
                }
            }

            userWriter.close();
            foodWriter.close();
            exerciseWriter.close();
            sleepWriter.close();

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readDataFromFile(String fileName) throws IOException {
        List<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        reader.close();
        return data;
    }

    public void loadData() {
        try {
            System.out.println("Loading data...");

            BufferedReader userReader = new BufferedReader(new FileReader("users.txt"));
            BufferedReader foodReader = new BufferedReader(new FileReader("food.txt"));
            BufferedReader exerciseReader = new BufferedReader(new FileReader("exercise.txt"));
            BufferedReader sleepReader = new BufferedReader(new FileReader("sleep.txt"));

            String line;

            // Load users
            while ((line = userReader.readLine()) != null) {
                String username = line.trim();
                User user = userManager.findUser(username);
                if (user == null) {
                    user = new User(username);
                    userManager.addUser(user);
                }
            }

            // Load food data
            while ((line = foodReader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                User user = userManager.findUser(username);
                if (user != null) {
                    try {
                        FoodItem food = new FoodItem(parts[1], Integer.parseInt(parts[2]), LocalDate.parse(parts[3]));
                        user.addFoodItem(food);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format in the food data: " + parts[3]);
                        // Handles the errors
                    }
                }
            }

            // Load exercise data
            while ((line = exerciseReader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                User user = userManager.findUser(username);
                if (user != null) {
                    try {
                        Exercise exercise = new Exercise(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), LocalDate.parse(parts[4]));
                        user.addExercise(exercise);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format in the exercise data: " + parts[4]);
                    }
                }
            }

            // Load sleep data
            while ((line = sleepReader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                User user = userManager.findUser(username);
                if (user != null) {
                    try {
                        SleepRecord sleepRecord = new SleepRecord(LocalTime.parse(parts[1]), LocalTime.parse(parts[2]), LocalDate.parse(parts[3]));
                        user.addSleepRecord(sleepRecord);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format in the sleep data: " + parts[1]);
                    }
                }
            }

            userReader.close();
            foodReader.close();
            exerciseReader.close();
            sleepReader.close();

            System.out.println(ConsoleColors.GREEN_BOLD +"Data loaded successfully."+ConsoleColors.RESET);
        } catch (FileNotFoundException e) {
            System.out.println("No data to load. This might be the first time running the program.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}