package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;

import javax.swing.*;
import java.awt.*;

/**
 * The homePanel class handles the switching between the panels.
 */
public class HomePanel {

    private final ExpenseManager expenseManager;

    private final CardLayout cardLayout;

    private final JPanel cardPanel;
    private final JPanel controlPanel;

    private final JButton switchUsers;
    private final JButton switchExpenses;
    private final JButton switchDebts;

    private final JButton addRemoveUser;
    private final UserForm userForm;
    private final JButton showUsers;
    private final UserDisplayPanel userDisplayPanel;

    private final JButton addExpense;
    private final ExpenseForm expenseForm;
    private final JButton showExpenses;
    private final ExpenseDisplayPanel expenseDisplayPanel;

    private final JButton computeDebts;
    private final DebtsPanel computeDebtsPanel;

    public HomePanel(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;

        // cardLayout is used to manage a stack of components where only one component (panel) is visible at a time.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Four main panels
        JPanel userPanel = createPanel("Users");
        JPanel expensePanel = createPanel("Expenses");
        JPanel debtsPanel = createPanel("Debts");
        controlPanel = createPanel("");

        cardPanel.add(userPanel, "Users");
        cardPanel.add(expensePanel, "Expenses");
        cardPanel.add(debtsPanel, "Debts");

        // three main buttons
        switchUsers = new JButton("Users");
        switchExpenses = new JButton("Expenses");
        switchDebts = new JButton("Debts");

        addSwitchUsersAction();
        addSwitchExpenseAction();
        addSwitchDebtsAction();

        controlPanel.add(switchUsers);
        controlPanel.add(switchExpenses);
        controlPanel.add(switchDebts);

        // based panels

        // display the users
        userDisplayPanel = new UserDisplayPanel(expenseManager);
        cardPanel.add(userDisplayPanel, "displayUsers");
        showUsers = new JButton("Show users");
        userPanel.add(showUsers);

        showUsersAction();
        showUserActionBackButton();

        // compute debts
        computeDebts = new JButton("Show debts");
        debtsPanel.add(computeDebts);
        computeDebtsPanel = new DebtsPanel(expenseManager);
        cardPanel.add(computeDebtsPanel, "debtsPanel");

        computeDebtsAction();
        computeDebtsActionbackButton();

        // add an expense
        addExpense = new JButton("Add expense");
        expensePanel.add(addExpense);
        expenseForm = new ExpenseForm(expenseManager);
        cardPanel.add(expenseForm, "expenseForm");

        addExpenseAction();
        addExpenseActionBackButton();

        // add or remove a user
        addRemoveUser = new JButton("Add / Remove user");
        userPanel.add(addRemoveUser);
        userForm = new UserForm(expenseManager, expenseForm);
        cardPanel.add(userForm, "userForm");

        addRemoveUserAction();
        addRemoveUserformBackAction();

        // display the expenses
        expenseDisplayPanel = new ExpenseDisplayPanel(expenseManager);
        cardPanel.add(expenseDisplayPanel, "displayExpenses");
        showExpenses = new JButton("Show expenses");
        expensePanel.add(showExpenses);

        showExpensesAction();
        showExpensesActionBackButton();


    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    private JPanel createPanel(String name) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        return panel;
    }

    // Panel switch actions

    private void addSwitchUsersAction(){
        this.switchUsers.addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "Users")));
    }

    private void addSwitchExpenseAction(){
        this.switchExpenses.addActionListener(listener ->
                SwingUtilities.invokeLater(()-> cardLayout.show(cardPanel, "Expenses")));
    }

    private void addSwitchDebtsAction(){
        this.switchDebts.addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "Debts")));
    }

    private void addRemoveUserAction(){
        this.addRemoveUser.addActionListener(listener ->
                SwingUtilities.invokeLater(()-> cardLayout.show(cardPanel, "userForm")));
    }

    private void addRemoveUserformBackAction(){
        this.userForm.getBackButton().addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "Users")));
    }

    private void showUsersAction() {
        this.showUsers.addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "displayUsers")));
    }

    private void showUserActionBackButton(){
        this.userDisplayPanel.getBackButton().addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "Users")));
    }

    private void addExpenseAction(){
        this.addExpense.addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "expenseForm")));

    }

    private void addExpenseActionBackButton(){
        this.expenseForm.getBackButton().addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "Expenses")));

    }

    private void showExpensesAction(){
        this.showExpenses.addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "displayExpenses")));
    }

    private void showExpensesActionBackButton(){
        this.expenseDisplayPanel.getBackButton().addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "Expenses")));
    }

    private void computeDebtsAction() {
        this.computeDebts.addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "debtsPanel")));
    }

    private void computeDebtsActionbackButton(){
        this.computeDebtsPanel.getBackButton().addActionListener(listener ->
                SwingUtilities.invokeLater(() -> cardLayout.show(cardPanel, "Debts")));
    }

}