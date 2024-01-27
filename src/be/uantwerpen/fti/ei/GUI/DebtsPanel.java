package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The DebtsPanel class displays the debts on the GUI in the for 'user owes user2 x amount'.
 */
public class DebtsPanel extends JPanel implements Constants {

    private final ExpenseManager expenseManager;
    private final JButton backButton;
    private final JButton updateDebts;
    private List<String> debtList;

    public DebtsPanel(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;

        setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(bbX, bbY, bWidth, bHeight);

        updateDebts = new JButton("Update debts");
        updateDebts.setBounds(175, 375, 150, bHeight);

        this.add(backButton);
        this.add(updateDebts);

        updateDebtsAction();
    }

    public JButton getBackButton() {
        return backButton;
    }

    private void updateDebtsAction() {
        this.updateDebts.addActionListener(listener -> SwingUtilities.invokeLater(() -> {
            debtList = expenseManager.settleDebt();
            repaint();
        }));
    }

    public void displayUsers(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Debts: ", 225, 50);
        if (debtList == null || debtList.isEmpty()){
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("No debts yet", 210, 100);
        } else {
            for (int i = 0; i < debtList.size(); i++){
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.drawString(debtList.get(i), 190, 80 + (20 * (i+1)));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        displayUsers(g);
    }



}
