package ui;

import model.Meal;
import model.MealPlan;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ui.GroceryListAppUI.*;

public class MealPlanWindow extends JFrame implements ActionListener {
    protected static final Color FRAME_BACKGROUND_COLOR = new Color(234, 222, 184);
    protected static final Color GREEN = new Color(160, 159, 87);
    protected static final Color ORANGE = new Color(197, 104, 36);
    private static final Dimension FRAME_SIZE = new Dimension(850,700);
    private static final Font TITLE_FONT = new Font("SANS-SERIF", Font.BOLD | Font.ITALIC, 20);
    private static final Font SUBTITLE_FONT = new Font("SANS-SERIF", Font.BOLD, 15);
    private static final Font TEXT_FONT = new Font("MONOSPACED", Font.PLAIN, 15);
    private static final Border HIGHLIGHTED_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GREEN, 5),
            BorderFactory.createEmptyBorder(10, 10, 10, 10));
    private final MealPlan mp;
    private JButton deleteMealButton;
    private JPanel mealPanels;
    private JPanel inputPanel;
    private JPanel selectedPane;

    public MealPlanWindow(MealPlan mp) {
        this.mp = mp;

        frameSetUp();
        setLayout(new BorderLayout());

        JPanel mealPlanScreen = initializeMainPanel();
        add(mealPlanScreen);

        initializeMealsPanel();
        initializeButtons();
    }

    private void initializeButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(0, 4, 20, 20));

        JButton addMealButton = drawButton("Add", "data/layer-plus.png");
        JButton saveMealPlanButton = drawButton("Save", "data/disk.png");
        JButton refreshButton = drawButton("Refresh", "data/refresh.png");
        deleteMealButton = drawButton("Delete", "data/layer-minus.png");

        buttons.add(addMealButton);
        buttons.add(saveMealPlanButton);
        buttons.add(deleteMealButton);
        buttons.add(refreshButton);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(buttons, BorderLayout.PAGE_END);
    }

    private JButton drawButton(String text, String iconPath) {
        ImageIcon buttonIcon = new ImageIcon(iconPath);
        ImageIcon scaledIcon = new ImageIcon(buttonIcon.getImage().getScaledInstance(30,
                30, Image.SCALE_DEFAULT));
        JButton newButton = new JButton(text, scaledIcon);

        newButton.setBackground(GREEN);
        newButton.setIconTextGap(20);
        newButton.setActionCommand(text);
        newButton.addActionListener(this);

        return newButton;
    }

    private void initializeMealsPanel() {
        mealPanels = new JPanel(new GridLayout(0, 1, 10, 10));
        mealPanels.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        List<Meal> meals = mp.getMeals();
        for (Meal m : meals) {
            JPanel meal = initializeIndividualMealPanel(m);
            meal.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Meal #" + (meals.indexOf(m) + 1)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            meal.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedPane = (JPanel) e.getComponent();
                    if (selectedPane.equals(meal)) {
                        meal.setBorder(HIGHLIGHTED_BORDER);
                        deleteMealButton.setBackground(ORANGE);
                    }
                }
            });
            mealPanels.add(meal);
        }

        JScrollPane mealsScrollPane = new JScrollPane(mealPanels, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(mealsScrollPane, BorderLayout.CENTER);
    }

    private JPanel initializeIndividualMealPanel(Meal m) {
        JPanel mealInfo = new JPanel();
        mealInfo.setLayout(new BorderLayout(20, 5));

        JLabel mealName = new JLabel(m.getMealName(), SwingConstants.CENTER);
        mealName.setFont(TITLE_FONT);
        mealName.setBackground(ORANGE);
        mealName.setForeground(Color.WHITE);
        mealName.setOpaque(true);
        mealName.setPreferredSize(new Dimension(FRAME_WIDTH / 3, FRAME_HEIGHT / 10));

        JPanel cookingTime = initializeTimePanel(m);

        JPanel ingredients = initializeIngredientsPanel(m.getIngredients());

        mealInfo.add(mealName, BorderLayout.LINE_START);
        mealInfo.add(cookingTime, BorderLayout.AFTER_LINE_ENDS);
        mealInfo.add(ingredients, BorderLayout.CENTER);

        return mealInfo;
    }

    private JPanel initializeTimePanel(Meal m) {
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BorderLayout(5, 5));
        JLabel heading = new JLabel("Cooking Time: ");
        heading.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(10, 0, 10, 20)));
        heading.setFont(SUBTITLE_FONT);

        JLabel description = new JLabel("   " + m.getCookingTime() + " min");
        description.setFont(TEXT_FONT);
        timePanel.add(heading, BorderLayout.PAGE_START);
        timePanel.add(description, BorderLayout.CENTER);

        return timePanel;
    }


    private JPanel initializeIngredientsPanel(List<String> ingredients) {
        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BorderLayout(5, 5));

        JPanel description = new JPanel();
        description.setLayout(new GridLayout(0, 2, 5, 5));

        JLabel heading = new JLabel("Ingredients: ");
        heading.setFont(SUBTITLE_FONT);
        heading.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(10, 0, 10, 20)));
        ingredientsPanel.add(heading, BorderLayout.PAGE_START);

        for (String i : ingredients) {
            JLabel ingredientLabel = new JLabel("• " + i);
            ingredientLabel.setFont(TEXT_FONT);
            description.add(ingredientLabel);
        }

        ingredientsPanel.add(description);

        return ingredientsPanel;
    }


    private JPanel initializeMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 50));

        return mainPanel;
    }


    private void frameSetUp() {
        setTitle("Meal Plan");
        setSize(FRAME_SIZE);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBackground(FRAME_BACKGROUND_COLOR);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save":
                saveMeals();
                break;

            case "Add":
                addNewMeal(mp);
                revalidate();
                break;

            case "Delete":
                deleteMeal(mp);
                deleteMealButton.setBackground(GREEN);
                revalidate();
                break;

            case "Refresh":
                updateMealPlanWindow();
                break;
        }

    }

    private void deleteMeal(MealPlan mp) throws NullPointerException {
        try {
            mealPanels.remove(selectedPane);
            JLabel selectedLabel = (JLabel) selectedPane.getComponent(0);
            String mealName = selectedLabel.getText();
            List<String> mealNames = mp.getNamesOfCurrentMeals();
            List<Meal> meals = mp.getMeals();
            int index = mealNames.indexOf(mealName);
            Meal meal = meals.get(index);
            mp.removeExistingMeal(meal);

        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null,
                    "Please Select A Meal and Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void addNewMeal(MealPlan mp) throws IllegalArgumentException {
        try {
            Map<String, String> nameAndTime = getNameAndTimeDialog();
            String name = nameAndTime.get("name");
            int time = Integer.parseInt(nameAndTime.get("time"));
            List<String> ingredientsNeeded = getIngredientsDialog();
            Meal newMeal = new Meal(name, ingredientsNeeded, time);
            mp.addNewMeal(newMeal);
            JPanel newMealPanel = initializeIndividualMealPanel(newMeal);
            newMealPanel.setBorder(HIGHLIGHTED_BORDER);
            mealPanels.add(newMealPanel);

        } catch (Exception e) {
            popUpMessage("Invalid Input, Please Try Again.", "Error", "error");

        }

    }

    private void updateMealPlanWindow() {
        removeAll();
        new MealPlanWindow(mp);
        validate();
        dispose();
    }

    private Map<String, String> getNameAndTimeDialog() {
        Map<String,String> generalInfoMap = new HashMap<>();
        inputPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        inputPanel.add(new JLabel("Name: "));
        JTextField name = new JTextField();
        inputPanel.add(name);
        inputPanel.add(new JLabel("Time To Cook: "));
        JTextField time = new JTextField();
        inputPanel.add(time);
        JOptionPane.showConfirmDialog(this, inputPanel, "Add General Meal Info",
                JOptionPane.OK_CANCEL_OPTION);
        generalInfoMap.put("name", name.getText());
        generalInfoMap.put("time", time.getText());

        return generalInfoMap;
    }

    private List<String> getIngredientsDialog() {
        JPanel inputPanel = new JPanel(new GridLayout(0,1,5,5));

        int numberOfIngredients = getNumberOfIngredientsDialog();
        JTextField[] ingredientTextFields = new JTextField[numberOfIngredients];
        for (int i = 0; i < numberOfIngredients; i++) {
            ingredientTextFields[i] = new JTextField();
            inputPanel.add(new JLabel("Ingredient # " + (i + 1) + ": "));
            inputPanel.add(ingredientTextFields[i]);
        }

        JOptionPane.showConfirmDialog(this, inputPanel, "Add Ingredients",
                JOptionPane.OK_CANCEL_OPTION);

        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < numberOfIngredients; i++) {
            ingredients.add(ingredientTextFields[i].getText());
        }

        return ingredients;
    }

    private int getNumberOfIngredientsDialog() {
        JPanel labelAndText = new JPanel();
        labelAndText.setLayout(new GridLayout(0, 1, 5, 5));
        labelAndText.add(new JLabel("Number Of Ingredients For This Meal: "));
        JTextField numberOfIngredients = new JTextField();
        labelAndText.add(numberOfIngredients);

        JOptionPane.showConfirmDialog(this, labelAndText,
                "Number of Ingredients", JOptionPane.OK_CANCEL_OPTION);

        return Integer.parseInt(numberOfIngredients.getText());
    }


    private void saveMeals() {
        savePopUp();
    }

    private void savePopUp() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(mp);
            jsonWriter.close();
            popUpMessage("Current Meal Plan Has Been Saved.", "Yay!", "info");
        } catch (FileNotFoundException e) {
            popUpMessage("Unable to Save :(", "Error", "error");
        }
    }

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

}
