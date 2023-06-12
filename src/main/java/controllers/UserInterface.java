package controllers;

import models.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private UserManager userManager;
    private Scanner scanner;
    private User currentUser;
    private FileManager fileManager;

    public UserInterface(UserManager userManager, FileManager fileManager) {
        this.userManager = userManager;
        this.scanner = new Scanner(System.in);
        this.fileManager = fileManager;
    }

    public void start() {
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println(ConsoleColors.YELLOW +"1. "+ ConsoleColors.RESET +"Create a new user");
            System.out.println(ConsoleColors.YELLOW +"2. "+ ConsoleColors.RESET +"Log in as an existing user");
            System.out.println(ConsoleColors.YELLOW +"3. "+ ConsoleColors.RESET +"Enter new health data");
            System.out.println(ConsoleColors.YELLOW +"4. "+ ConsoleColors.RESET +"View health data analyses");
            System.out.println(ConsoleColors.YELLOW +"5. "+ ConsoleColors.RED_BRIGHT +"Exit"+ConsoleColors.RESET);

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    createNewUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    enterHealthData();
                    break;
                case 4:
                    viewHealthDataAnalyses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 5.");
            }
        }
    }

    private void createNewUser() {
        scanner.nextLine();
        System.out.println("Enter a username for the new account:");
        String username = scanner.nextLine();
        if (userManager.findUser(username) != null) {
            System.out.println("That username is already taken. Please choose another one.");
        } else {
            userManager.createUser(username);
            System.out.println("Account successfully created and logged in as "+ConsoleColors.PURPLE_UNDERLINED+""+ username + "."+ ConsoleColors.RESET);
            try {
                fileManager.saveData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void loginUser() {
        scanner.nextLine();
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        User user = userManager.findUser(username);
        if (user != null) {
            userManager.login(username);
            System.out.println("Successfully logged in as"+ConsoleColors.PURPLE_UNDERLINED+" "+ username + "."+ ConsoleColors.RESET);
            fileManager.loadData();
        } else {
            System.out.println("No user found with that username.");
        }
    }


    private void enterHealthData() {
        User currentUser = userManager.getCurrentUser();
        if (currentUser == null) {
            System.out.println("No user logged in. Please log in to enter health data.");
            return;
        }
        System.out.println("Please choose the type of health data to enter:");
        System.out.println(ConsoleColors.YELLOW +"1. "+ ConsoleColors.RESET +"Food item");
        System.out.println(ConsoleColors.YELLOW +"2. "+ ConsoleColors.RESET +"Exercise activity");
        System.out.println(ConsoleColors.YELLOW +"3. "+ ConsoleColors.RESET +"Sleep record");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                enterFoodItem(currentUser);
                break;
            case 2:
                enterExercise(currentUser);
                break;
            case 3:
                enterSleepRecord(currentUser);
                break;
            default:
                System.out.println(ConsoleColors.RED_BOLD +"Invalid option. Please choose a number between 1 and 3."+ ConsoleColors.RESET);
        }
        try {
            fileManager.saveData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewHealthDataAnalyses() {
        User currentUser = userManager.getCurrentUser();

        if (currentUser == null) {
            System.out.println(ConsoleColors.RED_BOLD +"No user logged in. Please log in to view health data analyses."+ ConsoleColors.RESET);
            return;
        }

        List<FoodItem> foodItems = currentUser.getFoodItems();
        List<Exercise> exerciseActivities = currentUser.getExerciseActivities();
        List<SleepRecord> sleepRecords = currentUser.getSleepRecords();

        if (!foodItems.isEmpty() && !exerciseActivities.isEmpty() && !sleepRecords.isEmpty()) {
            double avgCalories = Analysis.calculateAverageCalories(foodItems);
            double totalExerciseDuration = Analysis.calculateTotalExerciseDuration(exerciseActivities);
            double avgSleepHours = Analysis.calculateAverageSleepHours(sleepRecords);

            //Calculates weekly averages based on daily averages
            double weeklyAvgCalories = avgCalories * 7;
            double weeklyTotalExerciseDuration = totalExerciseDuration * 7;
            double weeklyAvgSleepHours = avgSleepHours * 7;

            System.out.println("Average daily calories consumed: " + avgCalories);
            System.out.println("Total exercise duration this week: " + totalExerciseDuration);
            System.out.println("Average sleep hours per day: " + avgSleepHours);
            System.out.println("Weekly average calories consumed: " + weeklyAvgCalories);
            System.out.println("Weekly total exercise duration: " + weeklyTotalExerciseDuration);
            System.out.println("Weekly average sleep hours: " + weeklyAvgSleepHours);
        } else {
            System.out.println("No health data available for the current user.");
        }
    }

    private void enterFoodItem(User currentUser) {
        scanner.nextLine();
        System.out.println("Enter the name of the food item:");
        String foodName = scanner.nextLine();
        System.out.println("Enter the calorie count of the food item:");
        int calories = scanner.nextInt();
        System.out.println("Enter the date of consumption (in format YYYY-MM-DD):");
        scanner.nextLine();
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        FoodItem newFoodItem = new FoodItem(foodName, calories, date);
        currentUser.addFoodItem(newFoodItem);

        System.out.println("Food item has been successfully recorded.");
    }

    private void enterExercise(User currentUser) {
        scanner.nextLine();
        System.out.println("Enter the type of exercise:");
        String exerciseType = scanner.nextLine();
        System.out.println("Enter the duration of exercise in minutes:");
        int duration = scanner.nextInt();
        System.out.println("Enter the estimated calories burned:");
        int caloriesBurned = scanner.nextInt();
        System.out.println("Enter the date of the exercise (in format YYYY-MM-DD):");
        scanner.nextLine();
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        Exercise newExercise = new Exercise(exerciseType, duration, caloriesBurned, date);
        currentUser.addExercise(newExercise);

        System.out.println("Exercise has been successfully recorded.");
    }

    private void enterSleepRecord(User currentUser) {
        scanner.nextLine();
        System.out.println("Enter the date of the sleep record (in format YYYY-MM-DD):");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);
        System.out.println("Enter the time you went to sleep (in format HH:MM):");
        String sleepTimeStr = scanner.nextLine();
        LocalTime sleepTime = LocalTime.parse(sleepTimeStr);
        System.out.println("Enter the time you woke up (in format HH:MM):");
        String wakeupTimeStr = scanner.nextLine();
        LocalTime wakeupTime = LocalTime.parse(wakeupTimeStr);

        SleepRecord newSleepRecord = new SleepRecord(sleepTime, wakeupTime, date);
        currentUser.addSleepRecord(newSleepRecord);

        System.out.println("Sleep record has been successfully recorded.");
    }
}