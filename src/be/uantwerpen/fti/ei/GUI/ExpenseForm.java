package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.Split.EqualSplit;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseForm extends JPanel {
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
        expenseField.setBounds(200, 100, 100, 50);
        amountField = new JTextField(8);
        amountField.setBounds(200, 150, 100, 50);
        paidbyBox = new JComboBox<>();
        paidbyBox.setBounds(200, 200, 100, 50);
        expenseTypeBox = new JComboBox<>();
        expenseTypeBox.setBounds(200, 250, 100, 50);

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
        addExpenseButton.setBounds(200,375, 100, 30);
        add(addExpenseButton);
        addExpenseButtonAction();

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        add(backButton);


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
        label.setBounds(100, y, 100, 50);
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

    // incomplete
    public void addExpenseButtonAction(){
        this.addExpenseButton.addActionListener(listener -> {
            String expense = expenseField.getText();
            String expenseType = String.valueOf(expenseTypeBox.getSelectedItem());
            int amount = Integer.parseInt(amountField.getText());
            User paidBy = new User(String.valueOf(paidbyBox.getSelectedItem()));
            List<Split> splits = new ArrayList<>();
            for (int i = 0; i < listModel.size(); i++){
                if (expenseType.equals("EQUAL")){
                    Split split = new EqualSplit(listModel.get(i));
                    splits.add(split);
                }
            }
            expenseManager.addExpense(expense, expenseType, amount, paidBy, splits);
            expenseManager.showBalances();
            expenseManager.showExpenses();
        });
    }

}
