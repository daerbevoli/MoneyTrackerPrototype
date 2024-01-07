package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.Split.EqualSplit;
import be.uantwerpen.fti.ei.Split.ExactSplit;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseForm extends JPanel implements Constants {
    // public void addExpense(String name, String expenseType, double amount, User paidBy, List<Split> splits)
    // JTextfield name of expense (dinner, lunch, ..)
    // JTextfield amount
    // JComboBox paidBy with names of all users
    // JComboBox expenseType
    // JCheckBox splits

    private final ExpenseManager expenseManager;

    private final JTextField expenseField;
    private final JTextField amountField;
    private final JComboBox<String> paidbyBox;
    private final JComboBox<String> expenseTypeBox;
    private final DefaultListModel<User> listModel;
    private final DefaultListModel<String> nameModel = new DefaultListModel<>();

    private final List<JTextField> usersAmountField;
    private final List<JLabel> usersAmountName;


    private final JButton addExpenseButton;
    private final JButton backButton;

    public ExpenseForm(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;

        setLayout(null);

        JLabel expenseLabel = makeNameLabel("Name: ", 100); // expense name
        JLabel amountLabel = makeNameLabel("Amount: ", 150); // amount
        JLabel paidByLabel = makeNameLabel("Paid by: ", 200); // who paid
        JLabel expenseTypeLabel = makeNameLabel("Expense type: ", 250); // how its split
        JLabel splits = makeNameLabel("Splits: ", 300);

        add(expenseLabel);
        add(amountLabel);
        add(paidByLabel);
        add(expenseTypeLabel);
        add(splits);

        expenseField = new JTextField(8);
        expenseField.setBounds(200, 100, bWidth, bHeight);
        amountField = new JTextField(8);
        amountField.setBounds(200, 150, bWidth, bHeight);
        paidbyBox = new JComboBox<>();
        paidbyBox.setBounds(200, 200, bWidth, bHeight);
        expenseTypeBox = new JComboBox<>();
        expenseTypeBox.setBounds(200, 250, bWidth, bHeight);

        add(expenseField);
        add(amountField);
        add(paidbyBox);
        add(expenseTypeBox);

        fillExpenseTypeBox();
        listModel = new DefaultListModel<>();

        // Create a JList with the DefaultListModel
        JList<String> peopleList = new JList<>(nameModel);
        peopleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add the JList to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(peopleList);
        scrollPane.setBounds(200, 300, 100, 60);
        add(scrollPane);

        addExpenseButton = new JButton("Add");
        addExpenseButton.setBounds(200,375, bWidth, bHeight);
        add(addExpenseButton);
        addExpenseButtonAction();

        backButton = new JButton("Back");
        backButton.setBounds(bbX, bbY, bWidth, bHeight);
        add(backButton);

        usersAmountField = new ArrayList<>();
        usersAmountName = new ArrayList<>();
        expenseTypeBoxAction();


    }

    public void expenseTypeBoxAction(){
        this.expenseTypeBox.addActionListener(listener -> SwingUtilities.invokeLater(() -> {
            boolean isVisible = "EXACT".equals(expenseTypeBox.getSelectedItem());

            for (int i = 0; i < expenseManager.getUsers().getData().size(); i++) {
                JTextField amount = new JTextField(8);
                usersAmountField.add(amount);
                amount.setBounds(375, 200 + (i * 30), 75, 30);
                amount.setVisible(isVisible);
                add(amount);

                JLabel name = new JLabel(expenseManager.getUsers().getData().get(i).getName());
                usersAmountName.add(name);
                name.setBounds(325, 200 + (i * 30), 75, 30);
                name.setVisible(isVisible);
                add(name);
            }

        }));
    }

    public void addUserToSplits(User user){
        listModel.addElement(user);
        nameModel.addElement(user.getName());
    }

    public void removeUserFromSplits(User user){
        listModel.removeElement(user);
        nameModel.removeElement(user.getName());
    }

    private JLabel makeNameLabel(String name, int y){
        JLabel label = new JLabel(name);
        label.setBounds(100, y, bWidth, bHeight);
        return label;
    }

    // does not work properly yet
    public void addToPaidByBox(User user){
        paidbyBox.addItem(user.getName());
    }

    public void removeFromPaidByBox(User user){
        paidbyBox.removeItem(user.getName());
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
                    JOptionPane.showMessageDialog(this, "Please fill out all fields.");
                    return;
                }

                int amount = Integer.parseInt(amountText);
                User paidBy = new User(String.valueOf(paidbyBox.getSelectedItem()));
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
                            JOptionPane.showMessageDialog(this, "Invalid input for exact amounts");
                            return;
                        }
                    }
                }

                // Add expense and handle other tasks
                expenseManager.addExpense(expense, expenseType, amount, paidBy, splits);
                System.out.println(expense + " added");

                // Reset fields
                resetFields();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input, please check that you filled " +
                        "out everything correctly");
            }
        }));
    }

    private void resetFields() {
        expenseField.setText("");
        amountField.setText("");
        expenseTypeBox.setSelectedItem("EQUAL");
        paidbyBox.setSelectedItem(nameModel.get(0));

        for (JTextField text : usersAmountField) {
            text.setText("");
        }
    }


}
