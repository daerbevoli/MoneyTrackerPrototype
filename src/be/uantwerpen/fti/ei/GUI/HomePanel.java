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
    private final UserForm userForm;

    private final JButton switchUsers;
    private final JButton switchTickets;
    private final JButton switchFeed;

    private final JButton addUser;

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
        userForm = new UserForm();

        cardPanel.add(userForm, "userform");

        addUser = new JButton("add user");

        userPanel.add(addUser);
        addUserAction();
        addUserformOkAction();
        addUserformBackAction();

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
            expenseManager.addUser(new User(userForm.getUsername().getText()));
            userForm.getUsername().setText("");
        });
    }

}