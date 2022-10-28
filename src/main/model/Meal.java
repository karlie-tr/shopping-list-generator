package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents a meal that includes the name, the needed ingredients,
// and cooking time

public class Meal implements Writable {

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

    @Override
    public JSONObject mealToJson() {
        JSONObject json = new JSONObject();
        json.put("Cooking Time", time);
        json.put("Ingredients Needed", ingredientsJson());
        json.put("Name", mealName);
        return json;
    }

    // EFFECTS: convert ingredient list to Json Array
    private JSONArray ingredientsJson() {
        JSONArray ingredientsJsonArray = new JSONArray();

        for (String i : ingredientsNeeded) {
            ingredientsJsonArray.put(i);
        }

        return  ingredientsJsonArray;
    }
}
