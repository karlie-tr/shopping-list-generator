package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealPlanTest {

    private final List<String> overnightOatIngredientList = new ArrayList<>();
    private final List<String> salmonBowlIngredientList = new ArrayList<>();
    private final List<String> chickenBowlIngredientList = new ArrayList<>();
    private final List<String> bagelAndCreamCheeseIngredientList = new ArrayList<>();
    private final MealPlan mp = new MealPlan();

    private Meal oats;
    private Meal salmonBowl;
    private Meal chickenBowl;
    private Meal bagel;



    @BeforeEach
    public void setUp() {
        overnightOatIngredientList.add("oat");
        overnightOatIngredientList.add("granola");
        overnightOatIngredientList.add("milk");
        overnightOatIngredientList.add("yogurt");
        overnightOatIngredientList.add("strawberries");
        overnightOatIngredientList.add("blueberries");
        overnightOatIngredientList.add("mango");
        oats = new Meal("overnight oats",overnightOatIngredientList,10);

        salmonBowlIngredientList.add("rice");
        salmonBowlIngredientList.add("salmon");
        salmonBowlIngredientList.add("cucumber");
        salmonBowlIngredientList.add("kale");
        salmonBowlIngredientList.add("avocado");
        chickenBowlIngredientList.add("mango");
        salmonBowl = new Meal("salmon bowl",salmonBowlIngredientList,20);

        chickenBowlIngredientList.add("rice");
        chickenBowlIngredientList.add("chicken");
        chickenBowlIngredientList.add("cucumber");
        chickenBowlIngredientList.add("kale");
        chickenBowlIngredientList.add("avocado");
        chickenBowl = new Meal("chicken bowl",chickenBowlIngredientList,20);

        bagelAndCreamCheeseIngredientList.add("everything bagel");
        bagelAndCreamCheeseIngredientList.add("herb and garlic cream cheese");
        bagel = new Meal("bagel and cream cheese",bagelAndCreamCheeseIngredientList,5);

        mp.addNewMeal(bagel);
    }

    @Test
    public void addTwoDifferentRecipesTest() {
        MealPlan mp1 = mp;
        mp1.addNewMeal(oats);
        mp1.addNewMeal(chickenBowl);

        List<String> mealNames1 = new ArrayList<>();
        mealNames1.add(bagel.getMealName());
        mealNames1.add(oats.getMealName());
        mealNames1.add(chickenBowl.getMealName());

        assertEquals(3,mp1.getNumberOfMeals());
        assertEquals(mealNames1,mp1.getNamesOfCurrentMeals());
        assertEquals(35,mp1.getTotalCookingTime());
    }

    @Test
    public void addDuplicateRecipeTest() {
        MealPlan mp2 = mp;
        mp2.addNewMeal(bagel);
        mp2.addNewMeal(salmonBowl);

        List<String> mealNames2 = new ArrayList<>();
        mealNames2.add(bagel.getMealName());
        mealNames2.add(bagel.getMealName());
        mealNames2.add(salmonBowl.getMealName());

        assertEquals(3,mp2.getNumberOfMeals());
        assertEquals(mealNames2,mp2.getNamesOfCurrentMeals());
        assertEquals(30,mp2.getTotalCookingTime());
    }

}
