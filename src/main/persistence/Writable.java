package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: return JSON file
    JSONObject toJson();
}
