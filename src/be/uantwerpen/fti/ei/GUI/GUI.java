package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;

import javax.swing.*;
import java.awt.*;

// GUI class , yet to be implemented
public class GUI extends JFrame {

    private ExpenseManager expenseManager;
    private HomePanel homePanel;

    public GUI(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;

        homePanel = new HomePanel(expenseManager);
        this.add(homePanel.getCardPanel(), BorderLayout.CENTER);
        this.add(homePanel.getControlPanel(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);






    }


}
