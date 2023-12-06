package be.uantwerpen.fti.ei.ExpenseManager;

import be.uantwerpen.fti.ei.DB.Database;
import be.uantwerpen.fti.ei.DB.UserDB;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// expense manager / controller class
// controller in the MVC design pattern
public class ExpenseManager {

    private final Database expenses;
    private final Database users;
    Map<User, Map<User, Double>> balanceSheet;

    public ExpenseManager(Database users, Database expenses) {
        this.users = users;
        this.expenses = expenses;
        balanceSheet = new HashMap<>();
    }

    public void addUser(User user){
        users.addUser(user);
        balanceSheet.put(user, new HashMap<>());
    }

    public void addExpense(String name, String expenseType, double amount, User paidBy, List<Split> splits) {
        Expense expense = ExpenseFactory.createExpense(name, expenseType, amount, paidBy, splits);
        expenses.addExpense(expense);
        assert expense != null;
        for (Split split : expense.getSplits()) {
            User paidTo = split.getUser();
            Map<User, Double> balances = balanceSheet.get(paidBy);
            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }

    public void showExpenses(){
        expenses.printDb();
    }

    public void showUsers(){
        users.printDb();
    }

    public void showBalance(User user) {
        boolean isEmpty = true;
        for (Map.Entry<User, Double> userBalance : balanceSheet.get(user).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(user, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances() {
        boolean isEmpty = true;
        for (Map.Entry<User, Map<User, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<User, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(User user1, User user2, double amount) {
        String user1Name = user1.getName();
        String user2Name = user2.getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }
}