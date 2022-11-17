package ui;

import model.MealPlan;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ui.GroceryListAppUI.*;

public class GroceryListWindow extends JFrame {
    private JPanel groceryPanel;
    private List<String> groceryList;
    private static final Font TEXT_FONT = new Font("SANS_SERIF", Font.PLAIN, 12);

    public GroceryListWindow(MealPlan mp) {
        groceryList = mp.getGroceryList();

        frameSetUp();

        JPanel mainPanel = new JPanel();
        groceryPanel = createGroceryPanel();

        add(mainPanel);
        add(groceryPanel);

        setVisible(true);
    }

    private void frameSetUp() {
        setTitle("To Buy List");
        setSize(FRAME_WIDTH * 3 / 4, FRAME_HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private JPanel createGroceryPanel() {
        JPanel groceryPanel = new JPanel();
        groceryPanel.setLayout(new BorderLayout());
        groceryPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 50));

        JLabel header = new JLabel("<html> TO BUY:  <html>");

        header.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        groceryPanel.add(header, BorderLayout.PAGE_START);

        JPanel groceryListCheckBoxes = createNameAndQuantityCheckBox(groceryList);
        groceryPanel.add(groceryListCheckBoxes, BorderLayout.CENTER);

        return groceryPanel;
    }

    private JPanel createNameAndQuantityCheckBox(List<String> toBuyList) {
        JPanel checkList = new JPanel();
        checkList.setLayout(new GridLayout(0,1,2,2));
        checkList.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        Set<String> ingredientNames = new HashSet<>(toBuyList);

        for (String i: ingredientNames) {
            String checkBoxText = i + " x " + Collections.frequency(toBuyList, i);
            JPanel checkBox = createCheckBox(checkBoxText.toLowerCase());
            checkList.add(checkBox);
        }

        return checkList;

    }

    private JPanel createCheckBox(String text) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(0,1,2,2));

        JCheckBox item = new JCheckBox(text);
        item.setFont(TEXT_FONT);
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2,1,2,2),
                BorderFactory.createLineBorder(Color.BLACK,2)));

        itemPanel.add(item);

        return itemPanel;
    }
}