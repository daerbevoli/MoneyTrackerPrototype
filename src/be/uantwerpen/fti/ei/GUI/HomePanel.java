package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.awt.*;

public class HomePanel {

    private final ExpenseManager expenseManager;

    private final CardLayout cardLayout;

    private final JPanel cardPanel;
    private final JPanel controlPanel;

    private final JButton switchUsers;
    private final JButton switchTickets;
    private final JButton switchDebts;

    private final JButton addRemoveUser;
    private final UserForm userForm;
    private final JButton showUsers;
    private final UserDisplayPanel userDisplayPanel;

    private final JButton addExpense;
    private final ExpenseForm expenseForm;

    private final JButton computeDebts;
    private final DebtsPanel computeDebtsPanel;

    public HomePanel(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;

        // cardLayout is used to manage a stack of components where only one component (panel) is visible at a time.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Four main panels
        JPanel userPanel = createPanel("Users");
        JPanel ticketsPanel = createPanel("Tickets");
        JPanel debtsPanel = createPanel("Debts");
        controlPanel = createPanel("");

        cardPanel.add(userPanel, "Users");
        cardPanel.add(ticketsPanel, "Tickets");
        cardPanel.add(debtsPanel, "Debts");

        // two main buttons
        switchUsers = new JButton("Users");
        switchTickets = new JButton("Tickets");
        switchDebts = new JButton("Debts");

        addSwitchUsersAction();
        addSwitchTicketsAction();
        addSwitchDebtsAction();

        controlPanel.add(switchUsers);
        controlPanel.add(switchTickets);
        controlPanel.add(switchDebts);

        // Users based panels

        // add or remove a user
        addRemoveUser = new JButton("Add / Remove user");
        userPanel.add(addRemoveUser);
        userForm = new UserForm(expenseManager);
        cardPanel.add(userForm, "userform");

        addRemoveUserAction();
        addRemoveUserformBackAction();


        // display the users
        userDisplayPanel = new UserDisplayPanel(expenseManager);
        cardPanel.add(userDisplayPanel, "displayUsers");
        showUsers = new JButton("Show users");
        userPanel.add(showUsers);

        showUsersActions();
        addUserActionBackButton();

        // add an expense
        addExpense = new JButton("Add expense");
        ticketsPanel.add(addExpense);
        expenseForm = new ExpenseForm(expenseManager);
        cardPanel.add(expenseForm, "expenseform");

        addExpenseAction();
        addExpenseActionBackButton();

        // compute debts
        computeDebts = new JButton("Compute debts");
        debtsPanel.add(computeDebts);
        computeDebtsPanel = new DebtsPanel(expenseManager);
        cardPanel.add(computeDebtsPanel, "debtsPanel");

        computeDebtsAction();
        computeDebtsActionbackButton();

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

    private void addSwitchUsersAction(){
        this.switchUsers.addActionListener(listener -> cardLayout.show(cardPanel, "Users"));
    }

    private void addSwitchTicketsAction(){
        this.switchTickets.addActionListener(listener -> cardLayout.show(cardPanel, "Tickets"));
    }

    private void addSwitchDebtsAction(){
        this.switchDebts.addActionListener(listener -> cardLayout.show(cardPanel, "Debts"));
    }

    private void addRemoveUserAction(){
        this.addRemoveUser.addActionListener(listener -> cardLayout.show(cardPanel, "userform"));
    }

    private void addRemoveUserformBackAction(){
        this.userForm.getBackButton().addActionListener(listener -> cardLayout.show(cardPanel, "Users"));
    }

    private void showUsersActions(){
        this.showUsers.addActionListener(listener -> cardLayout.show(cardPanel, "displayUsers"));
        userDisplayPanel.repaint();
    }

    private void addUserActionBackButton(){
        this.userDisplayPanel.getBackButton().addActionListener(listener -> cardLayout.show(cardPanel, "Users"));
    }

    private void addExpenseAction(){
        this.addExpense.addActionListener(listener -> cardLayout.show(cardPanel, "expenseform"));

    }

    private void addExpenseActionBackButton(){
        this.expenseForm.getBackButton().addActionListener(listener -> cardLayout.show(cardPanel, "Tickets"));

    }

    private void computeDebtsAction(){
        this.computeDebts.addActionListener(listener -> cardLayout.show(cardPanel, "debtsPanel"));
        computeDebtsPanel.repaint();
    }

    private void computeDebtsActionbackButton(){
        this.computeDebtsPanel.getBackButton().addActionListener(listener -> cardLayout.show(cardPanel, "Debts"));
    }

}