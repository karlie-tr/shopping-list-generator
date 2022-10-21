package persistence;

import model.MealPlan;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonSaver {
    private final String destination;
    private PrintWriter writer;

    // EFFECTS: constructs writer to write to destination file
    public JsonSaver(String destination) {
        this.destination = destination;

    }

    // MODIFIES:
    // EFFECTS:
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFES:
    // EFFECTS:
    public void write(MealPlan mp) {
        JSONObject json = mp.toJson();

    }

    public void close() {
    }
}
