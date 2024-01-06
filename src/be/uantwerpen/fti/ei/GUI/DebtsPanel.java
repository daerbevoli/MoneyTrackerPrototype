package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.SubjectObservers.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DebtsPanel extends JPanel {

    private final ExpenseManager expenseManager;
    private final JButton backButton;
    private final List<Expense> expenses;
    private final List<String> debtList;

    public DebtsPanel(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;
        expenses = expenseManager.getExpenses().getData();
        debtList = expenseManager.settleDebt();

        setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);

        this.add(backButton);

    }

    public JButton getBackButton() {
        return backButton;
    }

    public void displayUsers(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Debts: ", 200, 50);
        if (expenses.isEmpty()){
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("No debts yet", 190, 100);
        } else {
            for (int i = 0; i < debtList.size(); i++){
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.drawString(debtList.get(i), 190, 60 + (20 * (i+1)));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayUsers(g);
    }



}
