package ui;

// Grocery list application

import model.Meal;
import model.MealPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroceryListApp {

    private final Scanner input;
    private final MealPlan mp;
    private boolean runProgram;

    public GroceryListApp() {
        input = new Scanner(System.in);
        runProgram = true;
        mp = new MealPlan();

        runGroceryListApp();
    }

    // EFFECTS: set up the console application
    private void runGroceryListApp() {
        System.out.println("Hello, please select from the options listed below :).");
        printOptions();
        String str;

        while (runProgram) {
            if (input.hasNext()) {
                str = input.nextLine();
                parseInput(str);
            }
        }
    }

    // EFFECTS: print out the application's function
    private void printOptions() {
        System.out.println("\n Select from: ");
        System.out.println("\t view -> view current meal plan");
        System.out.println("\t add -> add a meal to current meal plan");
        System.out.println("\t time -> the amount of time needed to finishing cooking the meal plan");
        System.out.println("\t grocery list -> view the current grocery list");
        System.out.println("\t return -> return to the available options");
        System.out.println("\t exit -> quit");
    }

    // EFFECTS: print out information of the meal plan or functions of the application depending on user's input
    @SuppressWarnings("methodlength")
    private void parseInput(String str) {
        final String VIEW_MEAL_PLAN_COMMAND = "view";
        final String ADD_MEAL_COMMAND = "add";
        final String BACK_COMMAND = "return";
        final String QUIT_COMMAND = "exit";
        final String LIST_COMMAND = "grocery list";
        final String TIME_COMMAND = "time";

        if (str.length() > 0) {
            switch (str) {
                case VIEW_MEAL_PLAN_COMMAND:
                    printMealPlan();
                    break;
                case ADD_MEAL_COMMAND:
                    addMeals();
                    break;
                case BACK_COMMAND:
                    printOptions();
                    break;
                case QUIT_COMMAND:
                    System.out.println("Quiting Application....");
                    runProgram = false;
                    break;
                case TIME_COMMAND:
                    printTotalTime();
                    break;
                case LIST_COMMAND:
                    printShoppingList();
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
                    break;
            }
        }
    }

    // EFFECTS: print shopping list based on the meal plan created;
    //          print an error message and display options if no meal plan is created
    private void printShoppingList() {
        if (mp.getNamesOfCurrentMeals().size() > 0) {
            System.out.println("This meal plan's grocery list: ");
            for (String item : mp.getGroceryList()) {
                System.out.println("\t" + item);
            }
        } else {
            System.out.println("No Meal Plan has been created yet. Please try again.");
            printOptions();
        }
    }

    // EFFECTS: print total cooking time of the meal plan created;
    //          print an error message and display options if no meal plan is created
    private void printTotalTime() {
        if (mp.getTotalCookingTime() > 0) {
            System.out.println("The time needed to prep for this meal plan is "
                    + mp.getTotalCookingTime() + " minutes");
        } else {
            System.out.println("No Meal Plan has been created yet. Please try again.");
            printOptions();
        }
    }

    // REQUIRES: numOfIngredients > 0; cookingTime > 0
    // MODIFIES: MealPlan
    // EFFECTS: create a Meal based on user's input;
    //          add the created meal to the Meal Plan
    private void addMeals() {
        Scanner scan = new Scanner(System.in);

        int numOfIngredients;
        int cookingTime;
        String mealName;
        List<String> ingredients = new ArrayList<>();

        System.out.println("What is the name is of the meal?");
        mealName = scan.nextLine();

        System.out.println("How many ingredients are there?");
        numOfIngredients = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < numOfIngredients; i++) {
            System.out.println("Enter the ingredient no." + (i + 1) + ": ");
            ingredients.add(scan.nextLine());
        }

        System.out.println("How long does it take to cook this meal?");
        cookingTime = Integer.parseInt(scan.nextLine());

        Meal m = new Meal(mealName, ingredients, cookingTime);
        mp.addNewMeal(m);
    }

    // EFFECTS: print names of meals in the current meal plan;
    //          print an error message and display options if no meal plan is created
    private void printMealPlan() {
        if (mp.getNamesOfCurrentMeals().size() > 0) {
            System.out.println("The current meals in this meal plan are: ");
            for (String name : mp.getNamesOfCurrentMeals()) {
                System.out.println("\t" + name);
            }
        } else {
            System.out.println("No Meal Plan has been created yet. Please try again.");
            printOptions();
        }
    }
}
