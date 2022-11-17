package persistence;

import model.Meal;
import model.MealPlan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads meal plan from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MealPlan read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMealPlan(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses meal plan from JSON object and returns it
    private MealPlan parseMealPlan(JSONObject jsonObject) {
        MealPlan mp = new MealPlan();
        addMeals(mp, jsonObject);
        return mp;
    }

    // MODIFIES: mp
    // EFFECTS: parses meals from JSON object
    private void addMeals(MealPlan mp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(mp, nextMeal);
        }
    }

    // MODIFIES: mp
    // EFFECTS: add meals from JSON object to meal plan
    private void addMeal(MealPlan mp, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        JSONArray ingredientListJson = jsonObject.getJSONArray("Ingredients Needed");
        List<String> ingredientList = new ArrayList<>();
        int cookingTime = jsonObject.getInt("Cooking Time");

        for (Object json : ingredientListJson) {
            String ingredient = String.valueOf(json);
            ingredientList.add(ingredient);
        }

        Meal m = new Meal(name, ingredientList, cookingTime);
        mp.addNewMeal(m);
    }

}
