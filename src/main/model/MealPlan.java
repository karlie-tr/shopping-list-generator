package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represent a meal plan consists of a list of meals, total cooking time, the number of meals and the list of meals'
// names and a grocery list

public class MealPlan implements Writable {

    private final List<Meal> meals;                // the meals in the meal plan
    private final List<String> mealsNames;         // Names of all meals in meal plan
    private final List<String> toBuyList;          // The grocery list of all the items
    private Integer totalCookingTime;        // The sum of all individual meal's cookingTime
    private Integer numberOfMeals;           // The number of meals in the meal plan

    public MealPlan() {
        this.meals = new ArrayList<>();
        this.totalCookingTime = 0;
        this.numberOfMeals = 0;
        this.mealsNames = new ArrayList<>();
        this.toBuyList = new ArrayList<>();
    }

    // MODIFIES: mealPlan
    // EFFECTS : add a meal for the meal plan, including its name and cooking time
    public void addNewMeal(Meal m) {
        List<String> ingredientsNeeded = m.getIngredients();

        meals.add(m);
        mealsNames.add(m.getMealName());
        totalCookingTime += m.getCookingTime();
        numberOfMeals++;
        toBuyList.addAll(ingredientsNeeded);
    }

    // REQUIRES: mealPlan != empty; m is in mealPlan
    // MODIFIES: mealPlan
    // EFFECTS : remove a meal from the meal plan, including its name and cooking time
    public void removeExistingMeal(Meal m) {
        List<String> ingredientsNeeded = m.getIngredients();

        meals.remove(m);
        mealsNames.remove(m.getMealName());
        totalCookingTime -= m.getCookingTime();
        numberOfMeals--;
        for (String ingredient : ingredientsNeeded) {
            if (!(toBuyList.contains(ingredient))) {
                toBuyList.remove(ingredient);
            }
        }
    }

    // getters
    public int getNumberOfMeals() {
        return numberOfMeals;
    }

    // getters
    public int getTotalCookingTime() {
        return totalCookingTime;
    }

    // getters
    public List<String> getNamesOfCurrentMeals() {
        return mealsNames;
    }

    // getters
    public List<String> getGroceryList() {
        return toBuyList;
    }

    // getters
    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

}
