package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;

/**
 * The UserForm class handles the input of users taking into account wrong inputs.
 */
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

        usernameLabel.setBounds(xPosLabel, 200, bWidth, bHeight);
        username.setBounds(xPosField, 200, bWidth, bHeight);
        addButton.setBounds(xPosField, 230, bWidth, bHeight);
        removeButton.setBounds(xPosField, 260, bWidth, bHeight);
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
            if (isInvalidUsername(username)) {
                return;
            }

            if (userAlreadyExists(username)) {
                return;
            }

            User user = new User(username);
            expenseManager.addUser(user);
            expenseForm.addToPaidByBox(user);
            expenseForm.addUserToSplits(user);
            this.username.setText("");
            showMessage("User " + user.getName() + " successfully added", "AddSuccess", JOptionPane.INFORMATION_MESSAGE);
        }));
    }

    public void removeButtonAction() {
        this.removeButton.addActionListener(listener -> SwingUtilities.invokeLater(() -> {
            String username = this.username.getText();
            if (isInvalidUsername(username)) {
                return;
            }

            User user = findUserByUsername(username);
            if (user != null) {
                if (expenseManager.getDebtMap().containsKey(user)) {
                    showMessage("User is in debt", "DebtError", JOptionPane.ERROR_MESSAGE);
                } else {
                    expenseManager.removeUser(user);
                    expenseForm.removeFromPaidByBox(user);
                    expenseForm.removeUserFromSplits(user);
                    this.username.setText("");
                    showMessage("User " + user.getName() + " successfully removed", "RemoveSuccess", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                showMessage("User to remove not found", "UserNotFoundError", JOptionPane.ERROR_MESSAGE);
            }
        }));
    }

    private boolean isInvalidUsername(String username) {
        if (username.isEmpty()) {
            showMessage("Username cannot be empty", "EmptyStringError", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean userAlreadyExists(String username) {
        for (User user : expenseManager.getUsers().getData()) {
            if (username.equals(user.getName())) {
                showMessage("User already exists. If new user with the same name, use enumeration",
                        "UserAlreadyExistsError", JOptionPane.ERROR_MESSAGE);
                this.username.setText("");
                return true;
            }
        }
        return false;
    }

    private User findUserByUsername(String username) {
        for (User user : expenseManager.getUsers().getData()) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

}