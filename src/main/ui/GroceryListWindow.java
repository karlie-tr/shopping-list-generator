package ui;

import model.MealPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

import static ui.GroceryListAppUI.FRAME_HEIGHT;
import static ui.GroceryListAppUI.FRAME_WIDTH;
import static ui.MealPlanWindow.GREEN;
import static ui.MealPlanWindow.ORANGE;

// Represent a JFrame that contains the grocery list
public class GroceryListWindow extends JFrame implements ActionListener {
    private static final Font TEXT_FONT = new Font("SANS_SERIF", Font.PLAIN, 12);
    private JPanel groceryPanel;
    private JButton addButton;

    // EFFECTS: create a new JFrame that has grocery list
    public GroceryListWindow(MealPlan mp) {
        List<String> groceryList = mp.getGroceryList();

        frameSetUp();

        JPanel mainPanel = new JPanel();
        groceryPanel = createGroceryPanel(groceryList);

        add(mainPanel);
        add(groceryPanel);

        setVisible(true);
    }

    // EFFECTS: set up the frame
    private void frameSetUp() {
        setTitle("To Buy List");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // EFFECTS: create a panel that contains all items in grocery list and a title
    private JPanel createGroceryPanel(List<String> groceryList) {
        groceryPanel = new JPanel();
        groceryPanel.setLayout(new BorderLayout());
        groceryPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 50));

        JLabel header = new JLabel("<html> TO BUY:  <html>");

        header.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        groceryPanel.add(header, BorderLayout.PAGE_START);

        JPanel groceryListCheckBoxes = createNameAndQuantityCheckBox(groceryList);
        groceryPanel.add(groceryListCheckBoxes, BorderLayout.CENTER);


        return groceryPanel;
    }

    // EFFECTS: create a panel for each item in the grocery list
    private JPanel createNameAndQuantityCheckBox(List<String> toBuyList) {
        JPanel checkList = new JPanel();
        checkList.setLayout(new GridLayout(0, 1, 2, 2));
        checkList.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Set<String> ingredientNames = new HashSet<>(toBuyList);

        for (String i : ingredientNames) {
            String checkBoxText = i + " x " + Collections.frequency(toBuyList, i);
            JPanel checkBox = createCheckBox(checkBoxText.toLowerCase());
            checkList.add(checkBox);
        }

        addButton = new JButton("ADD");
        addButton.setBackground(GREEN);
        addButton.setIconTextGap(20);
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);
        checkList.add(addButton);

        return checkList;

    }

    // EFFECTS: create the checkbox with the item name
    private JPanel createCheckBox(String text) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(0, 1, 2, 2));

        JCheckBox item = new JCheckBox(text);
        item.setFont(TEXT_FONT);
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 1, 2, 2),
                BorderFactory.createLineBorder(Color.BLACK, 2)));

        itemPanel.add(item);

        return itemPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addIngredient();
    }

    // EFFECTS: create a new panel where user can add to the grocery list;
    // do not modify the grocery list of loaded Meal Plan
    private void addIngredient() {
        JPanel checkList = (JPanel) groceryPanel.getComponent(1);
        checkList.remove(addButton);
        JTextField newItemField = new JTextField();
        checkList.add(newItemField);
        revalidate();

        newItemField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkList.remove(newItemField);
                    JPanel newCheckBox = createCheckBox(newItemField.getText().toLowerCase());
                    newCheckBox.setBackground(ORANGE);
                    checkList.add(newCheckBox);
                    checkList.add(addButton);
                    revalidate();
                }
            }
        });
    }
}