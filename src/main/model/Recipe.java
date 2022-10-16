package model;

import java.util.List;

// Represents a recipe that includes a type of food, their cooking method,
// and cooking time
public class Recipe {
    private List<String> ingredientsNeeded; // a list of ingredients required for the recipe
    private List<Recipe> availableRecipes;   // a list of available recipes
    private int time;                        // the time required to cook the meal (in minutes)

    /*
     * REQUIRES: cookingTime > 0
     * EFFECTS : create a new recipe that includes ingredients needed and cooking time in minutes;
     *           cooking time is a positive integer that represent the minutes needed to cook the meal;
     */
    public Recipe(List<String> ingredientsNeeded, int cookingTime) {
        // stub
    }

    /*
     * EFFECTS : add a recipe to the list of available recipes if it is not in the list
     */
    public void addRecipe(Recipe recipe) {
        // stub
    }

    /*
     * EFFECTS : return cooking time needed for a recipe
     */
    public int getCookingTime() {
        return 0;
    }

}
