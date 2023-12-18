package be.uantwerpen.fti.ei.GUI;

import javax.swing.*;

public class userForm extends JPanel {

    private JTextField username;
    private JButton ok;

    public userForm(){

        username = new JTextField(8);
        ok = new JButton("ok");
        this.add(username);
        this.add(ok);

    }

    public JButton getOk() {
        return ok;
    }
}
