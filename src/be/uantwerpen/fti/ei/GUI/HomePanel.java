package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.awt.*;

public class HomePanel {

    private final ExpenseManager expenseManager;

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    JPanel controlPanel = new JPanel();
    private final UserForm userForm;

    private final JButton addUser;


    private final JButton switchUsers;
    private final JButton switchTickets;
    private final JButton switchFeed;


    public HomePanel(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;

        // cardLayout is used to manage a stack of components where only one component (panel) is visible at a time.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel userPanel = createPanel("Users");
        JPanel ticketsPanel = createPanel("Tickets");
        JPanel feedPanel = createPanel("feed");


        userForm = new UserForm();
        addUser = new JButton("add user");
        addUser.setBounds(200, 200, 50, 50);

        userPanel.add(addUser);
        addUserAction();
        addUserformOkAction();

        cardPanel.add(userPanel, "Users");
        cardPanel.add(userForm, "userform");
        cardPanel.add(ticketsPanel, "Tickets");
        cardPanel.add(feedPanel, "feed");

        switchUsers = new JButton("Users");
        addSwitchUsersAction();

        switchTickets = new JButton("Tickets");
        addSwitchTicketsAction();

        switchFeed = new JButton("feed");
        addSwitchFeedAction();

        controlPanel.add(switchUsers);
        controlPanel.add(switchTickets);
        controlPanel.add(switchFeed);

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

    private void addUserformOkAction(){
        this.userForm.getOk().addActionListener(listener -> {
            cardLayout.show(cardPanel, "Users");
            expenseManager.addUser(new User(userForm.getUsername().getText()));
            userForm.getUsername().setText("");
        });
    }

}