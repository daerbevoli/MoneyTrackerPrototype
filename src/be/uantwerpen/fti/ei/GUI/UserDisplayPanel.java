package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.util.List;

public class UserDisplayPanel extends JPanel {

    private final List<User> users;
    private final JButton backButton;

    public UserDisplayPanel(List<User> users){
        this.users = users;

        setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        add(backButton);

        displayUsers();

    }

    // Yet to add a label when users is empty
    public void displayUsers(){
        for (int i = 0; i < users.size(); i++) {
            String userLabel = "User " + (i + 1) + ": " + users.get(i).getName();
            JLabel label = new JLabel(userLabel);
            label.setBounds(200, 20 * (i + 1), 100, 20);
            add(label);
        }
    }

    public JButton getBackButton() {
        return backButton;
    }
}
