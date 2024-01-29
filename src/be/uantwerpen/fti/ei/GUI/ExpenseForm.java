package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.Split.EqualSplit;
import be.uantwerpen.fti.ei.Split.ExactSplit;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;
import be.uantwerpen.fti.ei.ValidActivityNames;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ExpenseForm class handles the input of expenses taking into account wrong inputs.
 */
public class ExpenseForm extends JPanel implements Constants {

    private final ExpenseManager expenseManager;

    private final JTextField expenseField;
    private final JTextField amountField;
    private final JComboBox<String> paidByBox;
    private final JComboBox<String> expenseTypeBox;
    private final DefaultListModel<User> listModel;
    private final DefaultListModel<String> nameModel = new DefaultListModel<>();

    private final List<JTextField> usersAmountField;
    private final List<JLabel> usersAmountName;

    private final JButton addExpenseButton;
    private final JButton backButton;

    private JLabel validActivitiesLabel;
    private JList<String> validActivitiesList;
    private JScrollPane validActivitiesScrollPane;


    public ExpenseForm(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;

        setLayout(null);

        JLabel expenseLabel = makeNameLabel("Activity: ", 100);
        JLabel amountLabel = makeNameLabel("Amount: ", 150);
        JLabel paidByLabel = makeNameLabel("Paid by: ", 200);
        JLabel expenseTypeLabel = makeNameLabel("Type: ", 250);
        JLabel splits = makeNameLabel("Splits: ", 300);

        add(expenseLabel);
        add(amountLabel);
        add(paidByLabel);
        add(expenseTypeLabel);
        add(splits);

        expenseField = new JTextField(8);
        expenseField.setBounds(xPosField, 100, bWidth, bHeight);
        amountField = new JTextField(8);
        amountField.setBounds(xPosField, 150, bWidth, bHeight);
        paidByBox = new JComboBox<>();
        paidByBox.setBounds(xPosField, 200, bWidth, bHeight);
        expenseTypeBox = new JComboBox<>();
        expenseTypeBox.setBounds(xPosField, 250, bWidth, bHeight);

        add(expenseField);
        add(amountField);
        add(paidByBox);
        add(expenseTypeBox);

        fillExpenseTypeBox();
        listModel = new DefaultListModel<>();

        // Create a JList with the DefaultListModel
        JList<String> peopleList = new JList<>(nameModel);
        peopleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add the JList to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(peopleList);
        scrollPane.setBounds(xPosField, 300, bWidth, 60);
        add(scrollPane);

        addExpenseButton = new JButton("Add");
        addExpenseButton.setBounds(xPosField,375, bWidth, bHeight);
        add(addExpenseButton);
        addExpenseButtonAction();

        backButton = new JButton("Back");
        backButton.setBounds(bbX, bbY, bWidth, bHeight);
        add(backButton);

        usersAmountField = new ArrayList<>();
        usersAmountName = new ArrayList<>();
        expenseTypeBoxAction();

        // Create a label for "Valid Activities:"
        validActivitiesLabel = new JLabel("Valid Activities:");
        validActivitiesLabel.setBounds(330, 100, 150, 20); // Adjust these values as needed
        add(validActivitiesLabel);

        // Create a JList for valid activity names
        DefaultListModel<String> validActivitiesModel = new DefaultListModel<>();
        for (ValidActivityNames activity : ValidActivityNames.values()) {
            validActivitiesModel.addElement(activity.name());
        }
        validActivitiesList = new JList<>(validActivitiesModel);
        validActivitiesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add a ListSelectionListener to the validActivitiesList
        validActivitiesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // This condition prevents double events
                String selectedActivity = validActivitiesList.getSelectedValue();
                if (selectedActivity != null && !selectedActivity.isEmpty()) {
                    expenseField.setText(selectedActivity); // Set the selected activity to the expenseField
                }
            }
        });

        // Add the validActivitiesList to a JScrollPane
        validActivitiesScrollPane = new JScrollPane(validActivitiesList);
        validActivitiesScrollPane.setBounds(330, 120, 100, 50); // Adjust these values as needed
        validActivitiesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(validActivitiesScrollPane); // Add the scroll pane to the panel

    }


    public void addUserToSplits(User user){
        listModel.addElement(user);
        nameModel.addElement(user.getName());
        updateExactLabels();
    }

    public void removeUserFromSplits(User user){
        listModel.removeElement(user);
        nameModel.removeElement(user.getName());
        updateExactLabels();
    }

    private void updateExactLabels(){
        // Remove existing components
        for (JTextField amount : usersAmountField) {
            remove(amount);
        }
        usersAmountField.clear();

        for (JLabel name : usersAmountName) {
            remove(name);
        }
        usersAmountName.clear();

        // Call validate and repaint to apply changes
        validate();
        repaint();

        makeExactTypeAmountBoxes();
    }

    private void makeExactTypeAmountBoxes(){
        for (int i = 0; i < expenseManager.getUsers().getData().size(); i++) {
            JTextField amount = new JTextField(8);
            usersAmountField.add(amount);
            amount.setBounds(375, 200 + (i * 30), 75, 30);
            amount.setVisible(false);
            add(amount);

            JLabel name = new JLabel(expenseManager.getUsers().getData().get(i).getName() + " : ");
            usersAmountName.add(name);
            name.setBounds(325, 200 + (i * 30), 75, 30);
            name.setVisible(false);
            add(name);

        }
    }

    public void expenseTypeBoxAction(){
        this.expenseTypeBox.addActionListener(listener -> SwingUtilities.invokeLater(() -> {

            for (int i = 0; i < usersAmountField.size(); i++){
                if ("EXACT".equals(expenseTypeBox.getSelectedItem())){
                    usersAmountField.get(i).setVisible(true);
                    usersAmountName.get(i).setVisible(true);
                } else {
                    usersAmountField.get(i).setVisible(false);
                    usersAmountName.get(i).setVisible(false);
                }
            }

        }));
    }

    private JLabel makeNameLabel(String name, int y){
        JLabel label = new JLabel(name);
        label.setBounds(xPosLabel, y, bWidth, bHeight);
        return label;
    }

    public void addToPaidByBox(User user){
        paidByBox.addItem(user.getName());
    }

    public void removeFromPaidByBox(User user){
        paidByBox.removeItem(user.getName());
    }

    private void fillExpenseTypeBox(){
        expenseTypeBox.addItem("EQUAL");
        expenseTypeBox.addItem("EXACT");
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void addExpenseButtonAction() {
        this.addExpenseButton.addActionListener(listener -> SwingUtilities.invokeLater(() -> {
            try {
                // Validate input before processing
                String expense = expenseField.getText();
                String expenseType = String.valueOf(expenseTypeBox.getSelectedItem());
                String amountText = amountField.getText();

                if (expense.isEmpty() || expenseType.isEmpty() || amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill out all fields.", "incompleteFillError", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int amount = Integer.parseInt(amountText);
                User paidBy = null;
                for (User user : expenseManager.getUsers().getData()){
                    if (user.getName().equals(String.valueOf(paidByBox.getSelectedItem()))){
                        paidBy = user;
                        break;
                    }
                }
                List<Split> splits = new ArrayList<>();
                for (int i = 0; i < listModel.size(); i++) {
                    if (expenseType.equals("EQUAL")) {
                        Split split = new EqualSplit(listModel.get(i));
                        splits.add(split);
                    }
                    if (expenseType.equals("EXACT")) {
                        try {
                            int exactAmount = Integer.parseInt(usersAmountField.get(i).getText());
                            Split split = new ExactSplit(listModel.get(i), exactAmount);
                            splits.add(split);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Invalid input for exact amounts",
                                    "ExactValidateError", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                // Add expense and handle other tasks
                expenseManager.addExpense(expense, expenseType, amount, paidBy, splits);
                // JOptionPane.showMessageDialog(this, expense + " succesfully added", "AddSucces", JOptionPane.INFORMATION_MESSAGE);

                // Reset fields
                resetFields();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input, please check that you filled " +
                        "out everything correctly", "GeneralError", JOptionPane.ERROR_MESSAGE);
            }
        }));
    }

    private void resetFields() {
        expenseField.setText("");
        amountField.setText("");
        expenseTypeBox.setSelectedItem("EQUAL");
        paidByBox.setSelectedItem(nameModel.get(0));

        for (JTextField text : usersAmountField) {
            text.setText("");
        }
    }


}
