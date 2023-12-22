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

    private final JButton addUser;
    private final UserForm userForm;
    private final JButton showUsers;
    private final UserDisplayPanel userDisplayPanel;

    private JButton addExpense;
    private ExpenseForm expenseForm;

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
        addUser = new JButton("add user");
        userPanel.add(addUser);
        userForm = new UserForm();
        cardPanel.add(userForm, "userform");


        addUserAction();
        addUserformOkAction();
        addUserformBackAction();

        // display the users
        userDisplayPanel = new UserDisplayPanel(expenseManager.getUsers().getData());
        cardPanel.add(userDisplayPanel, "displayUsers");
        showUsers = new JButton("Show users");
        userPanel.add(showUsers);

        showUsersActions();
        addUserActionBackButton();

        /*
        * Things to add to users panel
        *
        * function to check whether a user with a given name is already in the database
        * remove user functionality, only if debt free
        * */

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


    private void addUserAction(){
        this.addUser.addActionListener(listener -> cardLayout.show(cardPanel, "userform"));
    }

    private void addUserformBackAction(){
        this.userForm.getBackButton().addActionListener(listener -> cardLayout.show(cardPanel, "Users"));
    }

    private void addUserformOkAction(){
        this.userForm.getOk().addActionListener(listener -> {
            String username = userForm.getUsername().getText();
            boolean existingUser = false;
            boolean emptyString = username.isEmpty();

            if (emptyString){
                JOptionPane.showMessageDialog(userForm, "Username cannot be empty");
            }
            for (User user : expenseManager.getUsers().getData()) {
                existingUser = username.equals(user.getName());
                if (existingUser) {
                    JOptionPane.showMessageDialog(userForm, "User already exist");
                    userForm.getUsername().setText("");
                }
            }
            if (!emptyString && !existingUser) {
                expenseManager.addUser(new User(userForm.getUsername().getText()));
                userDisplayPanel.displayUsers();
                userForm.getUsername().setText("");

            }
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
        expenseForm.fillPaidByBox();
    }

    private void addExpenseActionBackButton(){
        this.expenseForm.getBackButton().addActionListener(listener -> cardLayout.show(cardPanel, "Tickets"));
    }

}