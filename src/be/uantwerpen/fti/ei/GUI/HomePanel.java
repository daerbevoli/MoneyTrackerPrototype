package be.uantwerpen.fti.ei.GUI;

import javax.swing.*;
import java.awt.*;

public class HomePanel {

    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    JPanel controlPanel = new JPanel();




    private JPanel userPanel;
    private JButton addUser;

    private userForm userForm;


    private final JPanel ticketsPanel;
    private final JPanel feedPanel;

    private JButton switchUsers;
    private JButton switchTickets;
    private JButton switchFeed;


    public HomePanel() {
        // cardLayout is used to manage a stack of components where only one component (panel) is visible at a time.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        userForm = new userForm();
        addUser = new JButton("add user");

        userPanel = createPanel("Users");

        userPanel.add(addUser);
        addUserAction();

        userForm.getOk().addActionListener(listener -> {
            cardLayout.show(cardPanel, "Users");
        });

        ticketsPanel = createPanel("Tickets");
        feedPanel = createPanel("feed");

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
        this.switchUsers.addActionListener(listener -> {
            cardLayout.show(cardPanel, "Users");
        });
    }

    private void addSwitchTicketsAction(){
        this.switchTickets.addActionListener(listener -> {
            cardLayout.show(cardPanel, "Tickets");
        });
    }

    private void addSwitchFeedAction(){
        this.switchFeed.addActionListener(listener -> {
            cardLayout.show(cardPanel, "feed");
        });
    }

    private void addUserAction(){
        this.addUser.addActionListener(listener -> {
            cardLayout.show(cardPanel, "userform");
        });
    }

}