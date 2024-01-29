package be.uantwerpen.fti.ei.SubjectObservers;

import javax.swing.*;

public class usersObs implements Observer{
    private JFrame frame;
    private int userCounter;
    public usersObs(){
    }

    @Override
    public void update() {
        System.out.println("User added to database");
        System.out.flush();
        JOptionPane.showMessageDialog(frame, "User added to database", "Update", JOptionPane.INFORMATION_MESSAGE);
        userCounter += 1;
        System.out.println("Total users: " + userCounter);
    }
}
