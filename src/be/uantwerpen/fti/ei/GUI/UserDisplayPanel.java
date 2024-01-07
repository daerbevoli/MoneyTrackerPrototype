package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserDisplayPanel extends JPanel {

    private final ExpenseManager expenseManager;
    private final List<User> users;
    private final JButton backButton;

    public UserDisplayPanel(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;
        this.users = expenseManager.getUsers().getData();

        setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        add(backButton);

    }

    public void displayUsers(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Users: ", 200, 50);
        if (users.isEmpty()){
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("No users added yet", 190, 100);
        } else {
            for (int i = 0; i < users.size(); i++) {
                String userLabel = "User " + (i + 1) + ": " + users.get(i).getName();
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.drawString(userLabel, 190, 60 + (20 * (i+1)));
            }
        }
    }

    public JButton getBackButton() {
        return backButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayUsers(g);
    }
}
