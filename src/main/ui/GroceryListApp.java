package ui;

// Grocery list application

import model.Meal;
import model.MealPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GroceryListApp {

    public static final String JSON_STORE = "./data/MealPlan.json";
    private final Scanner input;
    private MealPlan mp;
    private boolean runProgram;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    public GroceryListApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        runProgram = true;
        mp = new MealPlan();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runGroceryListApp();
    }

    // EFFECTS: set up the console application
    private void runGroceryListApp() {
        System.out.println("Hello, please select from the options listed below :).");
        printMainOptions();
        String str;

        while (runProgram) {
            if (input.hasNext()) {
                str = input.nextLine();
                parseInput(str);
            }
        }
    }

    // EFFECTS: print the application's function
    private void printMainOptions() {
        System.out.println("\n Select from: ");
        System.out.println("\t new -> add new meal plan");
        System.out.println("\t load -> load previous meal plan");
        System.out.println("\t view -> view current meal plan");
        System.out.println("\t save -> save current meal plan");
        System.out.println("\t exit -> quit");
    }

    // EFFECTS: print out the options related to meal plan
    private void printMealPlanOptions() {
        System.out.println("\n Select from: ");
        System.out.println("\t view -> view current meal plan");
        System.out.println("\t add -> add a meal to current meal plan");
        System.out.println("\t remove -> remove a meal from the current meal plan");
        System.out.println("\t time -> the amount of time needed to finishing cooking the meal plan");
        System.out.println("\t grocery list -> view the current grocery list");
        System.out.println("\t return -> return to the main options");
        System.out.println("\t exit -> quit");
    }

    // EFFECTS: print out information of the meal plan or functions of the application depending on user's input
    @SuppressWarnings("methodlength")
    private void parseInput(String str) {
        final String VIEW_MEAL_PLAN_COMMAND = "view";
        final String NEW_MEAL_PLAN_COMMAND = "new";
        final String ADD_MEAL_COMMAND = "add";
        final String BACK_COMMAND = "return";
        final String QUIT_COMMAND = "exit";
        final String LIST_COMMAND = "grocery list";
        final String TIME_COMMAND = "time";
        final String REMOVE_COMMAND = "remove";
        final String SAVE_COMMAND = "save";
        final String LOAD_COMMAND = "load";

        if (str.length() > 0) {
            switch (str) {
                case NEW_MEAL_PLAN_COMMAND:
                    printMealPlanOptions();
                    break;
                case VIEW_MEAL_PLAN_COMMAND:
                    printMealPlan();
                    break;
                case BACK_COMMAND:
                    printMainOptions();
                    break;
                case QUIT_COMMAND:
                    System.out.println("Quiting Application....");
                    runProgram = false;
                    break;
                case SAVE_COMMAND:
                    saveMealPlan();
                    break;
                case LOAD_COMMAND:
                    loadMealPlan();
                    break;
                case TIME_COMMAND:
                    printTotalTime();
                    break;
                case LIST_COMMAND:
                    printGroceryList();
                    break;
                case REMOVE_COMMAND:
                    removeMeals();
                    break;
                case ADD_MEAL_COMMAND:
                    addMeals();
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
                    break;
            }
        }
    }

    // EFFECTS: print out no meal plan available message
    private void printNoMealPlan() {
        System.out.println("No Meal Plan has been created yet. Please try again.");

    }

    // EFFECTS: load a previously saved meal plan from file
    private void loadMealPlan() {
        try {
            mp = jsonReader.read();
            System.out.println("Loaded meal plan from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: save current Meal Plan to file
    private void saveMealPlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(mp);
            jsonWriter.close();
            System.out.println("Saved Current Meal Plan to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: remove an existing meal in mealPlan;
    //          print an error message if no meal plan is created;
    //          print an error message if meal is not in the current meal plan.
    private void removeMeals() {
        Scanner scan = new Scanner(System.in);
        String mealName;
        List<String> mealNames = mp.getNamesOfCurrentMeals();
        List<Meal> meals = mp.getMeals();

        if (mp.getNumberOfMeals() < 0) {
            printNoMealPlan();
        } else {
            printMealPlan();
            System.out.println("Which meal would you like to remove?");
            mealName = scan.nextLine();

            if (mealNames.contains(mealName)) {
                int index = mealNames.indexOf(mealName);
                Meal meal = meals.get(index);
                mp.removeExistingMeal(meal);
                System.out.println("Successfully removed the meal!");
            } else {
                System.out.println("This meal does not exist in our meal plan. Please choose another meal.");
            }
        }
    }

    // EFFECTS: print shopping list based on the meal plan created;
    //          print an error message and display options if no meal plan is created
    private void printGroceryList() {
        List<String> toBuyList = mp.getGroceryList();

        if (mp.getNumberOfMeals() > 0) {
            System.out.println("This meal plan's grocery list: ");
            printIngredientNameAndQuantity(toBuyList);
        } else {
            printNoMealPlan();
            printMealPlanOptions();
        }
    }

    // EFFECTS: print groceryList with the quantity of ingredients
    private void printIngredientNameAndQuantity(List<String> toBuyList) {
        Set<String> ingredientNames = new HashSet<>(toBuyList);

        for (String i : ingredientNames) {
            System.out.println("\t" + i + ": " + Collections.frequency(toBuyList, i));
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
            printMealPlanOptions();
        }
    }

    // REQUIRES: numOfIngredients > 0; cookingTime > 0
    // MODIFIES: MealPlan
    // EFFECTS: create a Meal based on user's input;
    //          add the created meal to the Meal Plan.
    private void addMeals() {
        Scanner scan = new Scanner(System.in);

        int numOfIngredients;
        int cookingTime;
        String mealName;
        List<String> ingredients = new ArrayList<>();

        System.out.println("What is the name is of the meal?");
        mealName = scan.nextLine();

        System.out.println("How long does it take to cook this meal?");
        cookingTime = Integer.parseInt(scan.nextLine());

        System.out.println("How many ingredients are there?");
        numOfIngredients = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < numOfIngredients; i++) {
            System.out.println("Enter the ingredient no." + (i + 1) + ": ");
            ingredients.add(scan.nextLine());
        }

        Meal m = new Meal(mealName, ingredients, cookingTime);

        mp.addNewMeal(m);
        System.out.println("Successfully added the meal!");
    }

    // EFFECTS: print names of meals in the current meal plan;
    //          print an error message if no meal plan is created
    private void printMealPlan() {
        if (mp.getNumberOfMeals() > 0) {
            System.out.println("The current meals in this meal plan are: ");
            for (String name : mp.getNamesOfCurrentMeals()) {
                System.out.println("\t" + name);
            }
        } else {
            printNoMealPlan();
        }
    }
}
