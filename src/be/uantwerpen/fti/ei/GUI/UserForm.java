package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;

public class UserForm extends JPanel implements Constants {

    private final ExpenseManager expenseManager;
    private final ExpenseForm expenseForm;

    private final JTextField username;
    private final JButton addButton;
    private final JButton removeButton;
    private final JButton backButton;


    public UserForm(ExpenseManager expenseManager, ExpenseForm expenseForm) {
        this.expenseManager = expenseManager;
        this.expenseForm = expenseForm;

        setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        username = new JTextField(8);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        backButton = new JButton("Back");

        // yet to make dimensions constant and consistent
        usernameLabel.setBounds(125, 200, bWidth, bHeight);
        username.setBounds(200, 200, bWidth, bHeight);
        addButton.setBounds(200, 230, bWidth, bHeight);
        removeButton.setBounds(200, 260, bWidth, bHeight);
        backButton.setBounds(bbX, bbY, bWidth, bHeight);

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

    public void addButtonAction() {
        this.addButton.addActionListener(listener -> SwingUtilities.invokeLater(() -> {
            String username = this.username.getText();
            boolean existingUser = false;
            boolean emptyString = username.isEmpty();

            if (emptyString) {
                JOptionPane.showMessageDialog(this, "Username cannot be empty", "EmptyStringError", JOptionPane.ERROR_MESSAGE);
            }
            for (User user : expenseManager.getUsers().getData()) {
                existingUser = username.equals(user.getName());
                if (existingUser) {
                    JOptionPane.showMessageDialog(this, "User already exist, If new user with same name" +
                            ", use enumeration", "UserAlreadyExistsError", JOptionPane.ERROR_MESSAGE);
                    this.username.setText("");
                    break;
                }
            }
            if (!emptyString && !existingUser) {
                User user = new User(username);
                expenseManager.addUser(user);
                expenseForm.addToPaidByBox(user);
                expenseForm.addUserToSplits(user);
                this.username.setText("");
                //JOptionPane.showMessageDialog(this, "User " + user.getName() +
             //           " successfully added", "AddSuccess", JOptionPane.INFORMATION_MESSAGE);
            }
        }));
    }

    private void removeButtonAction() {
        this.removeButton.addActionListener(listener -> SwingUtilities.invokeLater(() -> {
            String username = this.username.getText();
            boolean userFound = false;
            boolean emptyString = username.isEmpty();

            if (emptyString) {
                JOptionPane.showMessageDialog(this, "Username cannot be empty", "StringEmptyError", JOptionPane.ERROR_MESSAGE);
            }

            for (User user : expenseManager.getUsers().getData()) {
                if (user.getName().equals(username)){ // only able to remove if user is debt free
                    if (!expenseManager.getDebtMap().containsKey(user)){
                        userFound = true;
                        expenseManager.removeUser(user);
                        expenseForm.removeFromPaidByBox(user);
                        expenseForm.removeUserFromSplits(user);
                        this.username.setText("");
                        JOptionPane.showMessageDialog(this, "User " + user.getName() +
                                " successfully removed", "RemoveSuccess", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this, "User is in debt", "DebtError", JOptionPane.ERROR_MESSAGE);
                        this.username.setText("");
                        return;
                    }
                }
            }

            if (!userFound && !emptyString) {
                JOptionPane.showMessageDialog(this, "User to remove not found", "UserNotFoundError", JOptionPane.ERROR_MESSAGE);
                this.username.setText("");
            }
        }));
    }
}