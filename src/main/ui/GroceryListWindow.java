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

public class GroceryListWindow extends JFrame implements ActionListener {
    private static final Font TEXT_FONT = new Font("SANS_SERIF", Font.PLAIN, 12);
    private final List<String> groceryList;
    private JPanel groceryPanel;
    private JButton addButton;

    public GroceryListWindow(MealPlan mp) {
        groceryList = mp.getGroceryList();

        frameSetUp();

        JPanel mainPanel = new JPanel();
        groceryPanel = createGroceryPanel(groceryList);

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
                    List<String> newList = new ArrayList<>();
                    newList.add(newItemField.getText());
                    checkList.remove(newItemField);
                    newList.addAll(groceryList);
                    groceryPanel.remove(checkList);
                    JPanel newCheckList = createNameAndQuantityCheckBox(newList);
                    groceryPanel.add(newCheckList);
                    revalidate();
                }
            }
        });
    }
}