package be.uantwerpen.fti.ei.SubjectObservers;

import javax.swing.*;

/**
 * The user Observer class observes the user database and notifies when a user is added to the database.
 */
public class userObs implements Observer{
    private final JFrame frame;
    private int userCounter;
    public userObs(JFrame frame) {
        this.frame = frame;
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
