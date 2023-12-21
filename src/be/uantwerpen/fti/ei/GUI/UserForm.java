package be.uantwerpen.fti.ei.GUI;

import javax.swing.*;

public class UserForm extends JPanel {

    private final JTextField username;
    private final JButton okButton;
    private final JButton backButton;

    public UserForm() {

        setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        username = new JTextField(8);
        okButton = new JButton("OK");
        backButton = new JButton("Back");

        // yet to make dimensions constant and consistent
        usernameLabel.setBounds(125, 200, 75, 30);
        username.setBounds(200,200, 100, 30);
        okButton.setBounds(200, 230, 100, 30);
        backButton.setBounds(10, 10, 100, 30);

        this.add(usernameLabel);
        this.add(username);
        this.add(okButton);
        this.add(backButton);

    }

    public JButton getOk() {
        return okButton;
    }

    public JTextField getUsername() {
        return username;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
