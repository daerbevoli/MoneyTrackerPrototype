package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.Split.Split;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ExpenseDisplayPanel class displays the expenses in the form
 * 'user1 paid amount for activity to userx, ... split expenseType'.
 */
public class ExpenseDisplayPanel extends JPanel implements Constants {

    private final ExpenseManager expenseManager;
    private final List<Expense> expenses;
    private final JButton backButton;

    public ExpenseDisplayPanel(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;
        this.expenses = expenseManager.getExpenses().getData();

        setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(bbX, bbY, bWidth, bHeight);
        add(backButton);

    }

    public void displayExpenses(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Expenses: ", 150, 50);
        if (expenses.isEmpty()){
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("No expenses added yet", 100, 100);
        } else {
            for (int i = 0; i < expenses.size(); i++) {
                String expenseStr = getExpenseString(expenses.get(i));
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.drawString(expenseStr, 25, 80 + (40 * (i+1)));
            }
        }
    }

    private String getExpenseString(Expense expense) {
        String activity = expense.getName();
        String paidBy = expense.getPaidBy().getName();
        double amount = expense.getAmount();
        String type = expense.getExpenseType().toLowerCase();
<<<<<<< HEAD

        // List to hold names of other participants
        List<String> participantNames = new ArrayList<>();
        boolean paidByIncludedInSplits = false;

        // Iterate through splits to build the list of participants
        for (Split split : expense.getSplits()) {
            String participantName = split.getUser().getName();
            if (participantName.equals(paidBy)) {
                paidByIncludedInSplits = true; // Mark if 'paidBy' user is in the splits
=======
        StringBuilder splits = new StringBuilder();
        for (Split split : expense.getSplits()){
            if (split.getUser().getName().equals(paidBy)){
                splits.append(" self ");
>>>>>>> 69ce06ba4bcb5bd5df5faf6ae8a903d25212a613
            } else {
                participantNames.add(participantName); // Add other participants to the list
            }
        }

        // Build the splits string
        StringBuilder splits = new StringBuilder();
        if (paidByIncludedInSplits) {
            splits.append("himself");
            if (!participantNames.isEmpty()) {
                splits.append(", ");
            }
        }

        // Append other participants, separated by commas
        splits.append(String.join(", ", participantNames));

        return paidBy + " paid " + amount + " for " + activity + " to " + splits + ", split " + type;
    }
    public JButton getBackButton() {
        return backButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayExpenses(g);
    }


}
