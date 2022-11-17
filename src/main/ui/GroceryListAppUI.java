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

public class GroceryListAppUI extends JFrame implements ActionListener {

    public static final String JSON_STORE = "./data/MealPlan.json";
    protected static final int FRAME_WIDTH = 800;
    protected static final int FRAME_HEIGHT = 600;
    protected static final Font BUTTON_FONT = new Font("SANS_SERIF", Font.BOLD | Font.ITALIC, 20);
    protected static final Color BUTTON_BACKGROUND_COLOR = new Color(197, 104, 36);
    protected static final Color FRAME_BACKGROUND_COLOR = new Color(234, 222, 184);
    protected static final LineBorder BUTTON_BORDER = new LineBorder(Color.BLACK, 2, true);
    JPanel mainPanel;
    private MealPlan mp;
    private JButton viewTotalCookingTime;


    // EFFECTS: constructs a new window
    public GroceryListAppUI() {

        loadMealPlanPrompt();

        frameSetUp();

        mainPanel = createMainPanel();
        setUpMainScreen();
        add(mainPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveMealPlanPrompt();
            }
        });
    }

    public static void main(String[] args) {
        new GroceryListAppUI();
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 50));

        return mainPanel;
    }

    private void frameSetUp() {
        setTitle("Grocery List App");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBackground(FRAME_BACKGROUND_COLOR);
        setVisible(true);

    }

    private void setUpMainScreen() {
        setUpHeaders();
        setUpButtons();
    }

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

    private void setUpButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(3, 1, 20, 20));
        buttonPane.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 2));

        JButton viewMealPlan = drawButton("View");
        JButton viewGroceryList = drawButton("Grocery List");
        viewTotalCookingTime = drawButton("Total Cooking Time: " + mp.getTotalCookingTime() + " min");
        viewTotalCookingTime.setActionCommand("Refresh");
        ImageIcon refreshIcon = new ImageIcon("data/refresh.png");
        ImageIcon refreshIconScaled = new ImageIcon(refreshIcon.getImage().getScaledInstance(20,
                20, Image.SCALE_SMOOTH));
        viewTotalCookingTime.setIcon(refreshIconScaled);
        viewTotalCookingTime.setIconTextGap(15);

        buttonPane.add(viewMealPlan);
        buttonPane.add(viewGroceryList);
        buttonPane.add(viewTotalCookingTime);

        mainPanel.add(buttonPane);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Grocery List":
                new GroceryListWindow(mp);
                break;
            case "View":
                new MealPlanWindow(mp);
                break;
            case "Refresh":
                updateTotalTime(viewTotalCookingTime);
                break;
        }
    }

    private void updateTotalTime(JButton button) {
        button.setText("Total Cooking Time: " + mp.getTotalCookingTime() + " min");
    }

    private void saveMealPlanPrompt() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
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

    private void popUpMessage(String text, String titleOfPopUp, String type) {

        switch (type) {
            case "info":
                JOptionPane.showMessageDialog(null,
                        text, titleOfPopUp,JOptionPane.INFORMATION_MESSAGE);
                break;
            case "error":
                JOptionPane.showMessageDialog(null,
                        text, titleOfPopUp,JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void loadMealPlanPrompt() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);

        int n = JOptionPane.showConfirmDialog(this,
                "Would you like to load previously saved meal plan?",
                "Load Meal Plan?",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            try {
                mp = jsonReader.read();
                popUpMessage("File Loaded Successfully!", "Yay!", "info");
            } catch (IOException e) {
                popUpMessage("No Saved File Founded :(", "Error", "error");
                new MealPlan();
            }
        } else {
            mp = new MealPlan();
        }
    }


}


