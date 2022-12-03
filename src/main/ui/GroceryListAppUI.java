package ui;

import model.MealPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.GroceryListAppConsole.JSON_STORE;

// Represents an application that runs the graphical user interface

public class GroceryListAppUI extends JFrame implements ActionListener {
    protected static final int FRAME_WIDTH = 800;
    protected static final int FRAME_HEIGHT = 600;
    protected static final Font BUTTON_FONT = new Font("SANS_SERIF", Font.BOLD | Font.ITALIC, 20);
    protected static final Color BUTTON_BACKGROUND_COLOR = new Color(197, 104, 36);
    protected static final Color FRAME_BACKGROUND_COLOR = new Color(234, 222, 184);
    protected static final LineBorder BUTTON_BORDER = new LineBorder(Color.BLACK, 2, true);
    private final JPanel mainPanel;
    private JButton viewTotalCookingTime;
    protected MealPlan mp;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    // EFFECTS: constructs a new window
    public GroceryListAppUI() {
        frameSetUp();
        mainPanel = createMainPanel();
        setUpHeaders();
        setUpButtons();
        add(mainPanel);
    }

    // EFFECTS: run the application
    public static void main(String[] args) {
        new GroceryListAppUI();
    }

    // MODIFIES: this
    // EFFECTS: set up the layout and create white space between content and window borders
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 50));

        return mainPanel;
    }

    // MODIFIES: this
    // EFFECTS: set up the frame; set up prompt for when window is opened and closed
    protected void frameSetUp() {
        loadMealPlanPrompt();

        setTitle("Grocery List App");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setBackground(FRAME_BACKGROUND_COLOR);
        setVisible(true);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveMealPlanPrompt();
                mp.printEventLog();
            }
        });

    }


    // MODIFIES: mainPanel
    // EFFECTS: create headers for the left side of the frame and add them to mainPanel
    private void setUpHeaders() {
        JPanel greetingPanel = new JPanel();
        greetingPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JLabel header = new JLabel("<html> Hello! <br/> :) <html>");
        JLabel subtext = new JLabel("<html> Please select from the listed <html>"
                + "<html> options to view items related to your meal plan. <html>");
        header.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        subtext.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));

        JLabel groceryIcon = new JLabel();
        ImageIcon icon = new ImageIcon("data/app_welcome_icon.png");
        ImageIcon imageIcon = new ImageIcon(icon.getImage().getScaledInstance(FRAME_WIDTH / 5,
                FRAME_HEIGHT / 4, Image.SCALE_SMOOTH));
        groceryIcon.setIcon(imageIcon);

        greetingPanel.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 2));

        greetingPanel.add(header);
        greetingPanel.add(groceryIcon);
        greetingPanel.add(subtext);

        mainPanel.add(greetingPanel);
    }

    // MODIFIES: mainPanel
    // EFFECTS: create a panel for buttons and add them to mainPanel
    protected void setUpButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(4, 2, 20, 20));
        buttonPane.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 2));

        JButton viewMealPlanButton = drawButton("View");
        JButton groceryListButton = drawButton("Grocery List");
        JButton loadButton = drawButton("Load");

        viewTotalCookingTime = drawButton("Total Cooking Time: " + mp.getTotalCookingTime() + " min");
        viewTotalCookingTime.setActionCommand("Refresh");
        ImageIcon refreshIcon = new ImageIcon("data/refresh.png");
        ImageIcon refreshIconScaled = new ImageIcon(refreshIcon.getImage().getScaledInstance(20,
                20, Image.SCALE_SMOOTH));
        viewTotalCookingTime.setIcon(refreshIconScaled);
        viewTotalCookingTime.setIconTextGap(15);

        buttonPane.add(viewMealPlanButton);
        buttonPane.add(viewTotalCookingTime);
        buttonPane.add(groceryListButton);
        buttonPane.add(loadButton);
        
        mainPanel.add(buttonPane);
    }

    // EFFECTS: create buttons; set their command and listener
    private JButton drawButton(String text) {
        JButton newButton = new JButton(text);
        newButton.setActionCommand(text);
        newButton.addActionListener(this);
        newButton.setFont(BUTTON_FONT);
        newButton.setBackground(BUTTON_BACKGROUND_COLOR);
        newButton.setBorder(BUTTON_BORDER);
        newButton.setForeground(Color.WHITE);

        return newButton;
    }

    // EFFECTS: set actions for buttons when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Grocery List":
                new GroceryListWindow(mp);
                break;
            case "View":
                new MealPlanWindow(mp);
                break;
            case "Load":
                loadMealPlanPrompt();
            case "Refresh":
                updateTotalTime(viewTotalCookingTime);
                break;
        }
    }

    // EFFECTS: update the Total Cooking Time button to reflect changes in meal plan
    private void updateTotalTime(JButton button) {
        button.setText("Total Cooking Time: " + mp.getTotalCookingTime() + " min");
    }

    // EFFECTS: display a confirmation dialog; asks user if they want to save current meal plan
    private void saveMealPlanPrompt() {
        jsonWriter = new JsonWriter(JSON_STORE);
        int n = JOptionPane.showConfirmDialog(this,
                "Would you like to save your current meal plan?",
                "Save Meal Plan?",
                JOptionPane.YES_NO_OPTION);

        if (n == 0) {
            try {
                jsonWriter.open();
                jsonWriter.write(mp);
                jsonWriter.close();
                popUpMessage("Current Meal Plan Has Been Saved.", "Yay!", "info");
                dispose();
            } catch (FileNotFoundException e) {
                popUpMessage("Unable to Save :(", "Error", "error");
            }
        } else {
            dispose();
        }
    }

    // EFFECTS: create a popup message dialog
    private void popUpMessage(String text, String titleOfPopUp, String type) {
        switch (type) {
            case "info":
                JOptionPane.showMessageDialog(null,
                        text, titleOfPopUp, JOptionPane.INFORMATION_MESSAGE);
                break;
            case "error":
                JOptionPane.showMessageDialog(null,
                        text, titleOfPopUp, JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    // EFFECTS: display a confirmation dialog; asks user if they want to load saved meal plan
    private void loadMealPlanPrompt() {
        jsonReader = new JsonReader(JSON_STORE);
        int n = JOptionPane.showConfirmDialog(this,
                "Would you like to load previously saved meal plan?",
                "Load Meal Plan?",
                JOptionPane.YES_NO_OPTION);

        if (n == 0) {
            try {
                this.mp = jsonReader.read();
                popUpMessage("File Loaded Successfully!", "Yay!", "info");
            } catch (IOException e) {
                popUpMessage("No Saved File Founded :(", "Error", "error");
                new MealPlan();
            }
        } else {
            this.mp = new MealPlan();
        }
    }


}


