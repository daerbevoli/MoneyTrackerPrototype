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

/**
 * This is the GUI class. It represents the view in the MVC design pattern.
 * The class takes the databases and creates the expense manager with it. The expense manager is then given to
 * the home panel that interacts with the client.
 * The client inputs data on the GUI -> the expense manager then handles the input and changes the model
 * -> the database is changes -> the expense manager updates the GUI -> the GUI updates its displays.
 */
public class GUI extends JFrame implements Constants {

    public GUI(){
        Database<User> dbUsers = UserDB.getInstance();
        Database<Expense> dbExpenses = ExpenseDB.getInstance();
        ExpenseManager expenseManager = new ExpenseManager(dbUsers, dbExpenses);

        Observer expensesObs = new expenseObs(this);
        dbExpenses.register(expensesObs);
        Observer userObs = new userObs(this);
        dbUsers.register(userObs);

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
