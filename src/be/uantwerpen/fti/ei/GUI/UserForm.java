package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;

public class UserForm extends JPanel {

    private final ExpenseManager expenseManager;

    private final JTextField username;
    private final JButton addButton;
    private final JButton removeButton;
    private final JButton backButton;

    public UserForm(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;

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

        addButtonAction();
        removeButtonAction();

    }


    public JButton getBackButton() {
        return backButton;
    }

    public void addButtonAction(){
        this.addButton.addActionListener(listener -> {
            String username = this.username.getText();
            boolean existingUser = false;
            boolean emptyString = username.isEmpty();

            if (emptyString){
                JOptionPane.showMessageDialog(this, "Username cannot be empty");
            }
            for (User user : expenseManager.getUsers().getData()) {
                existingUser = username.equals(user.getName());
                if (existingUser) {
                    JOptionPane.showMessageDialog(this, "User already exist, If new user with same name" +
                            ", use enumeration");
                    this.username.setText("");
                }
            }
            if (!emptyString && !existingUser) {
                User user = new User(username);
                expenseManager.addUser(user);
                this.username.setText("");

            }
        });
    }

    private void removeButtonAction(){
        this.removeButton.addActionListener(listener -> {
            String username = this.username.getText();
            boolean userFound = false;
            boolean emptyString = username.isEmpty();

            if (emptyString){
                JOptionPane.showMessageDialog(this, "Username cannot be empty");
            }

            for (User currentUser : expenseManager.getUsers().getData()) {
                if (currentUser.getName().equals(username)) {
                    userFound = true;
                    expenseManager.removeUser(currentUser);
                    this.username.setText("");
                    break;
                }
            }

            if (!userFound && !emptyString) {
                JOptionPane.showMessageDialog(this, "User to remove not found or database is empty");
                this.username.setText("");
            }
        });
    }

}
