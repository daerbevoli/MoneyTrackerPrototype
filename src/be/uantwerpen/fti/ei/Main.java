package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.DB.Database;
import be.uantwerpen.fti.ei.DB.ExpenseDB;
import be.uantwerpen.fti.ei.DB.UserDB;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.GUI.GUI;
import be.uantwerpen.fti.ei.Split.EqualSplit;
import be.uantwerpen.fti.ei.Split.ExactSplit;
import be.uantwerpen.fti.ei.Split.Split;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Database<User> dbUsers = UserDB.getInstance();
        Database<Expense> dbExpenses = ExpenseDB.getInstance();
        ExpenseManager expenseManager = new ExpenseManager(dbUsers, dbExpenses);

        // the code on the console
        /*User sam = new User("sam");
        User john = new User("john");
        User steve = new User("steve");

        expenseManager.addUser(sam);
        expenseManager.addUser(john);
        expenseManager.addUser(steve);

        List<Split> splits = new ArrayList<>();
        splits.add(new EqualSplit(sam));
        splits.add(new EqualSplit(john));
        splits.add(new EqualSplit(steve));

        expenseManager.addExpense("dinner", "EQUAL", 300, sam, splits);

        List<String> ls = expenseManager.settleDebt();
        for (String str : ls ){
            System.out.println(str);
        }

        User joe = new User("joe");
        expenseManager.addUser(joe);
        List<Split> splits3 = new ArrayList<>();
        splits3.add(new EqualSplit(sam));
        splits3.add(new EqualSplit(john));
        splits3.add(new EqualSplit(steve));
        splits3.add(new EqualSplit(joe));

        expenseManager.addExpense("jetski", "EQUAL", 600, joe, splits3);

        ls = expenseManager.settleDebt();
        for (String str : ls ){
            System.out.println(str);
        }
        //
*/
        // the GUI
        // you need to put the code above between comments to run the gui on a clean slate
       GUI gui = new GUI(expenseManager);

        // TO DO
        // add label that user is added or removed
        // add label that expense is added
        // add one of the design pattern seen in the class
        // code cleanup

    }


}
