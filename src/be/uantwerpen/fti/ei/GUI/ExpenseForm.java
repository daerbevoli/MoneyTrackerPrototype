package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.util.List;

public class ExpenseForm extends JPanel {
    //     public void addExpense(String name, String expenseType, double amount, User paidBy, List<Split> splits) {
    // textfield name of expense (dinner, lunch, ..)
    // textfield amount
    // JComboBox paidBy with names of all users
    // JComboBox expenseType
    // JCheckBox splits

    private final ExpenseManager expenseManager;

    private JTextField expenseField;
    private JTextField amountField;

    private final JComboBox<String> paidbyBox;
    private final JComboBox<String> expenseTypeBox;

    private JButton backButton;

    public ExpenseForm(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;

        setLayout(null);

        JLabel expenseLabel = makeNameLabel("Name: ", 100,100, 100, 50); // expense name
        JLabel amountLabel = makeNameLabel("Amount: ", 100, 150, 100, 50); // amount
        JLabel paidByLabel = makeNameLabel("Paid by: ", 100, 200, 100, 50); // who paid
        JLabel expenseTypeLabel = makeNameLabel("Expense type: ", 100, 250, 100, 50); // how its split

        add(expenseLabel);
        add(amountLabel);
        add(paidByLabel);
        add(expenseLabel);

        expenseField = new JTextField(8);
        expenseField.setBounds(200, 100, 100, 50);
        amountField = new JTextField(8);
        amountField.setBounds(200, 150, 100, 50);
        paidbyBox = new JComboBox<>();
        paidbyBox.setBounds(200, 200, 100, 50);
        expenseTypeBox = new JComboBox<>();
        expenseTypeBox.setBounds(200, 250, 100, 50);

        fillPaidByBox();
        fillExpenseTypeBox();

        add(expenseField);
        add(amountField);
        add(paidbyBox);
        add(expenseTypeBox);

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        add(backButton);


    }

    private JLabel makeNameLabel(String name, int x, int y, int width, int height){
        JLabel label = new JLabel(name);
        label.setBounds(x, y, width, height);
        return label;
    }

    // does not work properly yet
    public void fillPaidByBox(){
        List<User> users = expenseManager.getUsers().getData();
        for (User user: users){
            paidbyBox.addItem(user.getName());
        }
    }

    private void fillExpenseTypeBox(){
        expenseTypeBox.addItem("EXACT");
        expenseTypeBox.addItem("EQUAL");
    }

    public JButton getBackButton() {
        return backButton;
    }
}
