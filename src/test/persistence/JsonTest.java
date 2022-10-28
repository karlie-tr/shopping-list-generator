package persistence;

import model.MealPlan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMealPlan(MealPlan mp,
                                 List<String> mealsName,
                                 Integer numberOfMeals,
                                 List<String> groceryList,
                                 Integer cookingTime) {

        assertEquals(numberOfMeals, mp.getNumberOfMeals());
        assertEquals(mealsName, mp.getNamesOfCurrentMeals());
        assertEquals(groceryList, mp.getGroceryList());
        assertEquals(cookingTime, mp.getTotalCookingTime());
    }
}
