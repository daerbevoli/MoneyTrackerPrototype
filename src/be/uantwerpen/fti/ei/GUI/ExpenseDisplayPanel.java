package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.Split.Split;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

    private String getExpenseString(Expense expense){
        String activity = expense.getName();
        String paidBy = expense.getPaidBy().getName();
        String amount = String.valueOf(expense.getAmount());
        String type = expense.getExpenseType().toLowerCase();
        StringBuilder splits = new StringBuilder();
        for (Split split : expense.getSplits()){
            if (split.getUser().getName().equals(paidBy)){
                splits.append(" self ");
            } else {
                splits.append(" ").append(split.getUser().getName());
            }
        }
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
