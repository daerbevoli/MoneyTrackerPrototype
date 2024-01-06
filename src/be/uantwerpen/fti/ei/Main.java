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

public class Main {

    public static void main(String[] args) {
        Database<User> dbUsers = UserDB.getInstance();
        Database<Expense> dbExpenses = ExpenseDB.getInstance();
        ExpenseManager expenseManager = new ExpenseManager(dbUsers, dbExpenses);

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



        User joe = new User("joe");
        expenseManager.addUser(joe);
        List<Split> splits3 = new ArrayList<>();
        splits3.add(new EqualSplit(sam));
        splits3.add(new EqualSplit(john));
        splits3.add(new EqualSplit(steve));
        splits3.add(new EqualSplit(joe));

        expenseManager.addExpense("jetski", "EQUAL", 600, joe, splits3);

        List<Split> splits4 = new ArrayList<>();
        splits4.add(new EqualSplit(sam));
        splits4.add(new EqualSplit(john));
        splits4.add(new EqualSplit(steve));
        splits4.add(new EqualSplit(joe));

        expenseManager.addExpense("Hiking", "EQUAL", 1000, john, splits4);


       GUI gui = new GUI(expenseManager);



    }


}
