package model;

import java.util.List;

// Represents a recipe that includes a type of food, their cooking method,
// and cooking time
public class Meal {
    private String mealName;                 // the name of the meal
    private List<String> ingredients;        // a list of ingredients required for the meal
    private int time;                        // the time required to cook the meal (in minutes)

    // REQUIRES: cookingTime > 0, ingredientsNeeded not empty
    // EFFECTS : create a new recipe that includes ingredients needed and cooking time in minutes;
    //           cooking time is a positive integer that represent the minutes needed to cook the meal;
    public Meal(String name, List<String> ingredientsNeeded, int cookingTime) {
        mealName = name;
        ingredients = ingredientsNeeded;
        time = cookingTime;
    }

    // getters
    public int getCookingTime() {
        return time;
    }

    // getters
    public String getMealName() {
        return mealName;
    }

}
