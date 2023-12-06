package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.DB.Database;
import be.uantwerpen.fti.ei.DB.ExpenseDB;
import be.uantwerpen.fti.ei.DB.UserDB;
import be.uantwerpen.fti.ei.ExpenseManager.ExpenseManager;
import be.uantwerpen.fti.ei.Split.EqualSplit;
import be.uantwerpen.fti.ei.Split.ExactSplit;
import be.uantwerpen.fti.ei.Split.Split;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Database db = UserDB.getInstance();
        Database db2 = ExpenseDB.getInstance();
        ExpenseManager expenseManager = new ExpenseManager(db, db2);

        User sam = new User("sam");
        User john = new User("john");
        User steve = new User("steve");

        expenseManager.addUser(sam);
        expenseManager.addUser(john);
        expenseManager.addUser(steve);

        List<Split> splits = new ArrayList<>();
        splits.add(new EqualSplit(sam));
        splits.add(new EqualSplit(john));
        splits.add(new EqualSplit(steve));

        List<Split> splits2 = new ArrayList<>();
        splits2.add(new ExactSplit(sam, 125));
        splits2.add(new ExactSplit(john, 175));
        splits2.add(new ExactSplit(steve, 100));


        expenseManager.addExpense("dinner", "EQUAL", 300, sam, splits);
        expenseManager.addExpense("lunch" , "EXACT", 400, steve, splits2);
        expenseManager.showBalance(john);
        expenseManager.showExpenses();
        expenseManager.showUsers();
    }


}
