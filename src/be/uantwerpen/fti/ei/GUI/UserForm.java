package be.uantwerpen.fti.ei.GUI;

import javax.swing.*;

public class UserForm extends JPanel {

    private final JTextField username;
    private final JButton addButton;
    private final JButton removeButton;
    private final JButton backButton;

    public UserForm() {

        setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        username = new JTextField(8);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        backButton = new JButton("Back");

        // yet to make dimensions constant and consistent
        usernameLabel.setBounds(125, 200, 75, 30);
        username.setBounds(200,200, 100, 30);
        addButton.setBounds(200, 230, 100, 30);
        removeButton.setBounds(200, 260, 100, 30);
        backButton.setBounds(10, 10, 100, 30);

        this.add(usernameLabel);
        this.add(username);
        this.add(addButton);
        this.add(removeButton);
        this.add(backButton);

    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JTextField getUsername() {
        return username;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
