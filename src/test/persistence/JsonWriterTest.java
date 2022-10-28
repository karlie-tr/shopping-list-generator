package persistence;

import model.Meal;
import model.MealPlan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    private final List<String> overnightOatIngredientList = new ArrayList<>();

    @Test
    void testWriterInvalidFile() {
        try {
            MealPlan mp = new MealPlan();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MealPlan mp = new MealPlan();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMealPlan.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMealPlan.json");
            mp = reader.read();
            assertEquals(0, mp.getNumberOfMeals());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            overnightOatIngredientList.add("oat");
            overnightOatIngredientList.add("milk");
            overnightOatIngredientList.add("strawberries");
            overnightOatIngredientList.add("mango");
            Meal oats = new Meal("overnight oats", overnightOatIngredientList, 10);
            MealPlan mp = new MealPlan();
            mp.addNewMeal(oats);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMealPlan.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMealPlan.json");
            mp = reader.read();
            List<String> names = new ArrayList<>();
            names.add("overnight oats");

            checkMealPlan(mp,
                    names,
                    1,
                    overnightOatIngredientList,
                    oats.getCookingTime());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
