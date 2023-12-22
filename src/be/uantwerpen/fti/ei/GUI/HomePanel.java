package be.uantwerpen.fti.ei.GUI;

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
    private final JButton switchFeed;

    private final JButton addRemoveUser;
    private final UserForm userForm;
    private final JButton showUsers;
    private final UserDisplayPanel userDisplayPanel;

    private final JButton addExpense;
    private final ExpenseForm expenseForm;

    public HomePanel(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;

        // cardLayout is used to manage a stack of components where only one component (panel) is visible at a time.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Four main panels
        JPanel userPanel = createPanel("Users");
        JPanel ticketsPanel = createPanel("Tickets");
        JPanel feedPanel = createPanel("feed");
        controlPanel = createPanel("");

        cardPanel.add(userPanel, "Users");
        cardPanel.add(ticketsPanel, "Tickets");
        cardPanel.add(feedPanel, "feed");

        // three main buttons
        switchUsers = new JButton("Users");
        switchTickets = new JButton("Tickets");
        switchFeed = new JButton("feed");

        addSwitchUsersAction();
        addSwitchTicketsAction();
        addSwitchFeedAction();

        controlPanel.add(switchUsers);
        controlPanel.add(switchTickets);
        controlPanel.add(switchFeed);

        // Users based panels

        // add a user
        addRemoveUser = new JButton("Add / Remove user");
        userPanel.add(addRemoveUser);
        userForm = new UserForm();
        cardPanel.add(userForm, "userform");


        addRemoveUserAction();
        addRemoveUserformAddAction();
        addRemoveUserformRemoveAction();
        addRemoveUserformBackAction();


        // display the users
        userDisplayPanel = new UserDisplayPanel(expenseManager.getUsers().getData());
        cardPanel.add(userDisplayPanel, "displayUsers");
        showUsers = new JButton("Show users");
        userPanel.add(showUsers);

        showUsersActions();
        addUserActionBackButton();

        addExpense = new JButton("Add expense");
        ticketsPanel.add(addExpense);
        expenseForm = new ExpenseForm(expenseManager);
        cardPanel.add(expenseForm, "expenseform");

        addExpenseAction();
        addExpenseActionBackButton();

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

    private void addSwitchFeedAction(){
        this.switchFeed.addActionListener(listener -> cardLayout.show(cardPanel, "feed"));
    }

    private void addRemoveUserAction(){
        this.addRemoveUser.addActionListener(listener -> cardLayout.show(cardPanel, "userform"));
    }

    private void addRemoveUserformBackAction(){
        this.userForm.getBackButton().addActionListener(listener -> cardLayout.show(cardPanel, "Users"));
    }

    private void addRemoveUserformAddAction(){
        this.userForm.getAddButton().addActionListener(listener -> {
            String username = userForm.getUsername().getText();
            boolean existingUser = false;
            boolean emptyString = username.isEmpty();

            if (emptyString){
                JOptionPane.showMessageDialog(userForm, "Username cannot be empty");
            }
            for (User user : expenseManager.getUsers().getData()) {
                existingUser = username.equals(user.getName());
                if (existingUser) {
                    JOptionPane.showMessageDialog(userForm, "User already exist, If new user with same name" +
                            ", use enumeration");
                    userForm.getUsername().setText("");
                }
            }
            if (!emptyString && !existingUser) {
                expenseManager.addUser(new User(username));
                userForm.getUsername().setText("");
                expenseForm.addToPaidByBox(username);
                expenseForm.addUserToSplits(username);

            }
            userDisplayPanel.repaint();
        });
    }

    private void addRemoveUserformRemoveAction(){
        this.userForm.getRemoveButton().addActionListener(listener -> {
            String username = userForm.getUsername().getText();
            boolean userFound = false;
            boolean emptyString = username.isEmpty();

            if (emptyString){
                JOptionPane.showMessageDialog(userForm, "Username cannot be empty");
            }

            for (User currentUser : expenseManager.getUsers().getData()) {
                if (currentUser.getName().equals(username)) {
                    userFound = true;
                    expenseManager.removeUser(currentUser);
                    userForm.getUsername().setText("");
                    expenseForm.removeFromPaidByBox(username);
                    expenseForm.removeUserFromSplits(username);
                    break;
                }
            }

            if (!userFound && !emptyString) {
                JOptionPane.showMessageDialog(userForm, "User to remove not found or database is empty");
                userForm.getUsername().setText("");
            }
            userDisplayPanel.repaint();
        });
    }

    private void showUsersActions(){
        this.showUsers.addActionListener(listener -> cardLayout.show(cardPanel, "displayUsers"));
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

}