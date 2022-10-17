package model;

import java.util.List;

// Represents a meal that includes the name, the needed ingredients,
// and cooking time
public class Meal {

    private final String mealName;                       // the name of the meal
    private final List<String> ingredientsNeeded;        // a list of ingredients required for the meal
    private final int time;                              // the time required to cook the meal (in minutes)

    // REQUIRES: cookingTime > 0, ingredientsNeeded not empty
    // MODIFIES: this
    // EFFECTS : create a new recipe that includes ingredients needed and cooking time in minutes;
    //           cooking time is a positive integer that represent the minutes needed to cook the meal;
    public Meal(String name, List<String> ingredients, int cookingTime) {
        mealName = name;
        ingredientsNeeded = ingredients;
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

    // getters
    public List<String> getIngredients() {
        return ingredientsNeeded;
    }

}
