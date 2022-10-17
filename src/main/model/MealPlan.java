package model;

import java.util.ArrayList;
import java.util.List;

public class MealPlan {

    private List<Meal> meals;
    private Integer totalCookingTime;
    private Integer numberOfMeals;
    private List<String> mealsNames;

    public MealPlan() {
        this.totalCookingTime = 0;
        this.meals = new ArrayList<>();
        this.numberOfMeals = 0;
        this.mealsNames = new ArrayList<>();
    }

    // EFFECTS : add a meal for the meal plan, including its name and cooking time
    public void addNewMeal(Meal m) {
        meals.add(m);
        mealsNames.add(m.getMealName());
        totalCookingTime += m.getCookingTime();
        numberOfMeals++;

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
    public List<Meal> getCurrentMeals() {
        return meals;
    }
}
