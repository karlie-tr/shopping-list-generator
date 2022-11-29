package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            new GroceryListApp();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found. Please Try Again");
        }
    }
}
