package be.uantwerpen.fti.ei.GUI;

import javax.swing.*;
import java.awt.*;

public class UserForm extends JPanel {

    private JTextField username;
    private JButton ok;
    private JLabel usernameLabel;

    public UserForm() {

        setLayout(null);
        username = new JTextField(8);
        ok = new JButton("ok");
        usernameLabel = new JLabel("username: ");
        username.setBounds(200,350, 100, 30);
        ok.setBounds(200, 380, 100, 30);
        usernameLabel.setBounds(username.getX()-75, username.getY(), 75, 30);

        this.add(username);
        this.add(ok);
        this.add(usernameLabel);

    }

    public JButton getOk() {
        return ok;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public void setOk(JButton ok) {
        this.ok = ok;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }
}
