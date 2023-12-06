package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;

import javax.swing.*;

// GUI class , yet to be implemented
public class GUI extends JFrame {

    private ExpenseManager expenseManager;

    public GUI(ExpenseManager expenseManager){
        this.expenseManager = expenseManager;

        this.setSize(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);



    }


}
