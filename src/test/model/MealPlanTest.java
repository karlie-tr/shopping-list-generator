package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealPlanTest {

    private final List<String> overnightOatIngredientList = new ArrayList<>();
    private final List<String> chickenBowlIngredientList = new ArrayList<>();
    private final List<String> bagelAndCreamCheeseIngredientList = new ArrayList<>();
    private final MealPlan mp1 = new MealPlan();
    private final MealPlan mp2 = new MealPlan();
    private final MealPlan mp3 = new MealPlan();

    private Meal oats;
    private Meal chickenBowl;
    private Meal bagel;

    @BeforeEach
    public void setUp() {
        overnightOatIngredientList.add("oat");
        overnightOatIngredientList.add("milk");
        overnightOatIngredientList.add("strawberries");
        overnightOatIngredientList.add("mango");

        chickenBowlIngredientList.add("rice");
        chickenBowlIngredientList.add("chicken");
        chickenBowlIngredientList.add("avocado");

        bagelAndCreamCheeseIngredientList.add("everything bagel");
        bagelAndCreamCheeseIngredientList.add("herb and garlic cream cheese");

        oats = new Meal("overnight oats",overnightOatIngredientList,10);
        chickenBowl = new Meal("chicken bowl",chickenBowlIngredientList,20);
        bagel = new Meal("bagel and cream cheese",bagelAndCreamCheeseIngredientList,5);
    }

    @Test
    public void addTwoDifferentMealsTest() {
        List<String> mealNames1 = new ArrayList<>();
        List<String> grocery1 = new ArrayList<>();

        mp1.addNewMeal(oats);
        mp1.addNewMeal(chickenBowl);
        mealNames1.add(oats.getMealName());
        mealNames1.add(chickenBowl.getMealName());
        assertEquals(2,mp1.getNumberOfMeals());
        assertEquals(mealNames1,mp1.getNamesOfCurrentMeals());

        grocery1.add("oat");
        grocery1.add("milk");
        grocery1.add("strawberries");
        grocery1.add("mango");
        grocery1.add("rice");
        grocery1.add("chicken");
        grocery1.add("avocado");
        assertEquals(grocery1,mp1.getGroceryList());

        assertEquals(30,mp1.getTotalCookingTime());
    }

    @Test
    public void addADuplicateMealTest() {
        List<String> mealNames2 = new ArrayList<>();
        List<String> grocery2 = new ArrayList<>();

        mp2.addNewMeal(bagel);
        mp2.addNewMeal(bagel);
        mealNames2.add(bagel.getMealName());
        mealNames2.add(bagel.getMealName());
        assertEquals(2,mp2.getNumberOfMeals());
        assertEquals(mealNames2,mp2.getNamesOfCurrentMeals());

        grocery2.add("everything bagel");
        grocery2.add("herb and garlic cream cheese");
        assertEquals(grocery2,mp2.getGroceryList());

        assertEquals(10,mp2.getTotalCookingTime());
    }

    @Test
    public void removeMultipleMealsTest() {
        List<String> mealNames3 = new ArrayList<>();
        List<Meal> meals3 = new ArrayList<>();
        List<String> grocery3 = new ArrayList<>();

        mp3.addNewMeal(bagel);
        mp3.addNewMeal(chickenBowl);
        mp3.addNewMeal(oats);
        mp3.removeExistingMeal(bagel);
        mp3.removeExistingMeal(oats);
        mealNames3.add(chickenBowl.getMealName());
        meals3.add(chickenBowl);
        assertEquals(1,mp3.getNumberOfMeals());
        assertEquals(mealNames3,mp3.getNamesOfCurrentMeals());
        assertEquals(meals3,mp3.getMeals());

        grocery3.add("rice");
        grocery3.add("chicken");
        grocery3.add("avocado");
        assertEquals(grocery3,mp3.getGroceryList());

        assertEquals(20,mp3.getTotalCookingTime());
    }
}
