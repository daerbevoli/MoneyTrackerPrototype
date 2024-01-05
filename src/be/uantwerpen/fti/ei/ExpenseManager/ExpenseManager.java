package be.uantwerpen.fti.ei.ExpenseManager;

import be.uantwerpen.fti.ei.DB.Database;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.*;

// expense manager / controller class
// controller in the MVC design pattern
public class ExpenseManager {

    private final Database<Expense> expenses;
    private final Database<User> users;
    private final Map<User, Map<User, Double>> balanceSheet;

    public ExpenseManager(Database<User> users, Database<Expense> expenses) {
        this.users = users;
        this.expenses = expenses;
        balanceSheet = new HashMap<>();
    }

    public void addUser(User user){
        users.addEntry(user);
        balanceSheet.put(user, new HashMap<>());
        users.notifyObserver();
    }

    public void removeUser(User user){
        users.removeEntry(user);
    }

    public void addExpense(String name, String expenseType, double amount, User paidBy, List<Split> splits) {
        Expense expense = ExpenseFactory.createExpense(name, expenseType, amount, paidBy, splits);
        expenses.addEntry(expense);

        for (Split split : expense.getSplits()) {
            User paidTo = split.getUser();
            updateBalances(paidBy, paidTo, split.getAmount());
        }
        expenses.notifyObserver();
    }

    private void updateBalances(User paidBy, User paidTo, double amount) {
        Map<User, Double> balances = balanceSheet.computeIfAbsent(paidBy, k -> new HashMap<>());
        balances.put(paidTo, balances.getOrDefault(paidTo, 0.0) + amount);

        balances = balanceSheet.computeIfAbsent(paidTo, k -> new HashMap<>());
        balances.put(paidBy, balances.getOrDefault(paidBy, 0.0) - amount);
    }

    // yet to add a debt settling algo that minimizes the total mount of transactions

    public void showExpenses(){
        expenses.printDb();
    }

    public void showUsers(){
        if (users.isEmpty()){
            System.out.println("database is empty");
        }
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

    /*
    correct:

    john owes sam: 75.0
    john owes steve: 200.0


    /*public void showBalances(){
        settleDebts(balanceSheet);
    }*/

    private void printBalance(User user1, User user2, double amount) {
        String user1Name = user1.getName();
        String user2Name = user2.getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

    public Database<User> getUsers() {
        return users;
    }
}