package be.uantwerpen.fti.ei.SubjectObservers;

import javax.swing.*;

/**
 * The expense observer class observes the expenses database and notifies when an expense is added to the database.
 */
public class expenseObs implements Observer{

    private JFrame frame;
    private int expenseCounter;

    public expenseObs(JFrame frame) {
    }

    @Override
    public void update() {
        System.out.println("Expense added to database");
        JOptionPane.showMessageDialog(frame, "Expense added to database", "Expense Update", JOptionPane.INFORMATION_MESSAGE);
        expenseCounter += 1;
        System.out.println("Total expenses: " + expenseCounter);
        System.out.flush();
    }
}
