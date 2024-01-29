package be.uantwerpen.fti.ei.SubjectObservers;

import javax.swing.JOptionPane;

import javax.swing.*;

public class expensesObs implements Observer{

    private JFrame frame;
    private int expenseCounter;
    public expensesObs(JFrame frame) {
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
