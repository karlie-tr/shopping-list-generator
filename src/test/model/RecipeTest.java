package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    private List<String> overnightOatIngredientList = new ArrayList<>();
    private List<String> salmonBowlIngredientList = new ArrayList<>();
    private List<String> chickenBowlIngredientList = new ArrayList<>();
    private List<String> bagelAndCreamCheeseIngredientList = new ArrayList<>();
    private List<Recipe> mealPlan = new ArrayList<>();

    private Recipe overnightOat;
    private Recipe salmonBowl;
    private Recipe bagel;
    private Recipe chickenBowl;


    @BeforeEach
    public void setUp() {
        overnightOatIngredientList.add("oat");
        overnightOatIngredientList.add("granola");
        overnightOatIngredientList.add("milk");
        overnightOatIngredientList.add("yogurt");
        overnightOatIngredientList.add("strawberries");
        overnightOatIngredientList.add("blueberries");
        overnightOatIngredientList.add("mango");
        Recipe overnightOat = new Recipe(overnightOatIngredientList,10);

        salmonBowlIngredientList.add("rice");
        salmonBowlIngredientList.add("salmon");
        salmonBowlIngredientList.add("cucumber");
        salmonBowlIngredientList.add("kale");
        salmonBowlIngredientList.add("avocado");
        chickenBowlIngredientList.add("mango");
        Recipe salmonBowl = new Recipe(salmonBowlIngredientList,20);

        chickenBowlIngredientList.add("rice");
        chickenBowlIngredientList.add("chicken");
        chickenBowlIngredientList.add("cucumber");
        chickenBowlIngredientList.add("kale");
        chickenBowlIngredientList.add("avocado");
        Recipe chickenBowl = new Recipe(chickenBowlIngredientList,20);

        bagelAndCreamCheeseIngredientList.add("everything bagel");
        bagelAndCreamCheeseIngredientList.add("herb and garlic cream cheese");
        Recipe bagel = new Recipe(bagelAndCreamCheeseIngredientList,5);

        mealPlan.add(bagel);
    }

    @Test
    public void addTwoDifferentRecipesTest() {
        List<Recipe> mealPlan1 = mealPlan;
        mealPlan1.add(overnightOat);
        mealPlan1.add(chickenBowl);

        assertEquals(3,mealPlan1.size());
        assertEquals(bagel,mealPlan1.get(0));
        assertEquals(overnightOat,mealPlan1.get(1));
        assertEquals(chickenBowl,mealPlan1.get(2));
    }

    @Test
    public void addDuplicateRecipeTest() {
        List<Recipe> mealPlan2 = mealPlan;
        mealPlan2.add(bagel);
        mealPlan2.add(chickenBowl);

        assertEquals(2,mealPlan2.size());
        assertEquals(bagel,mealPlan2.get(0));
        assertEquals(chickenBowl,mealPlan2.get(1));
    }

    @Test
    public void addRecipesWithDuplicateIngredientTest() {
        List<Recipe> mealPlan3 = mealPlan;
        mealPlan3.add(salmonBowl);
        mealPlan3.add(chickenBowl);

        assertEquals(2,mealPlan3.size());
        assertEquals(salmonBowl,mealPlan3.get(0));
        assertEquals(chickenBowl,mealPlan3.get(1));

    }
}
