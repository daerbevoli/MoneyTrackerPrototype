package be.uantwerpen.fti.ei.GUI;

import be.uantwerpen.fti.ei.Constants;
import be.uantwerpen.fti.ei.DB.Database;
import be.uantwerpen.fti.ei.DB.ExpenseDB;
import be.uantwerpen.fti.ei.DB.UserDB;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.SubjectObservers.*;
import be.uantwerpen.fti.ei.User;

import javax.swing.*;
import java.awt.*;

// GUI = view
public class GUI extends JFrame implements Constants {

    public GUI(){
        Database<User> dbUsers = UserDB.getInstance();
        Database<Expense> dbExpenses = ExpenseDB.getInstance();
        ExpenseManager expenseManager = new ExpenseManager(dbUsers, dbExpenses);

        Observer expensesObs = new expensesObs();
        dbExpenses.register(expensesObs);
        Observer usersObs = new usersObs();
        dbUsers.register(usersObs);

        HomePanel homePanel = new HomePanel(expenseManager);
        this.add(homePanel.getCardPanel(), BorderLayout.CENTER);
        this.add(homePanel.getControlPanel(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREENWIDTH, SCREENHEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
}
