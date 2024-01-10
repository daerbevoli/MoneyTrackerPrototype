package be.uantwerpen.fti.ei.ExpenseManager;

import be.uantwerpen.fti.ei.DB.Database;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.*;

// expense manager = controller & facade
public class ExpenseManager {

    private final Database<Expense> expenses;
    private final Database<User> users;
    private final Map<User, Map<User, Double>> balanceSheet;
    private final Map<User, Double> debtMap;

    public ExpenseManager(Database<User> users, Database<Expense> expenses) {
        this.users = users;
        this.expenses = expenses;
        balanceSheet = new HashMap<>();
        debtMap = new TreeMap<>();
    }

    public void addUser(User user){
        users.addEntry(user);
        balanceSheet.put(user, new HashMap<>());
    }

    public void removeUser(User user){
        users.removeEntry(user);
    }

    public void addExpense(String name, String expenseType, double amount, User paidBy, List<Split> splits) throws Exception {
        Expense expense = ExpenseFactory.createExpense(name, expenseType, amount, paidBy, splits);
        expenses.addEntry(expense);

        for (Split split : expense.getSplits()) {
            User paidTo = split.getUser();
            updateBalances(paidBy, paidTo, split.getAmount());
        }
    }

    private void updateBalances(User paidBy, User paidTo, double amount) {
        Map<User, Double> balances = balanceSheet.computeIfAbsent(paidBy, k -> new HashMap<>());
        balances.put(paidTo, balances.getOrDefault(paidTo, 0.0) + amount);

        balances = balanceSheet.computeIfAbsent(paidTo, k -> new HashMap<>());
        balances.put(paidBy, balances.getOrDefault(paidBy, 0.0) - amount);
    }


    public List<String> settleDebt() {
        List<String> debtList = new ArrayList<>();

        // Calculate total debt for each user
        for (Map.Entry<User, Map<User, Double>> balance : balanceSheet.entrySet()) {
            double totAmount = 0;
            for (Map.Entry<User, Double> bal : balance.getValue().entrySet()) {
                totAmount += bal.getValue();
            }
            debtMap.put(balance.getKey(), totAmount);
        }

        // Identify and settle debts for each debtor
        for (Map.Entry<User, Double> entry : debtMap.entrySet()) {
            User debtor = entry.getKey();
            double debtorDebt = entry.getValue();

            if (debtorDebt < 0) {
                debtList.addAll(settleDebts(debtor, debtorDebt));
            }
        }
        return debtList;
    }

    private List<String> settleDebts(User debtor, double debt) {
        List<String> debtStr = new ArrayList<>();

        for (Map.Entry<User, Double> entry : debtMap.entrySet()) {
            User creditor = entry.getKey();
            double creditorAmount = entry.getValue();

            if (creditorAmount > 0) {
                double settledAmount = Math.min(-debt, creditorAmount);

                debtStr.add(debtor.getName() + " owes " + creditor.getName() + " : " + settledAmount);

                // Update the individual balances
                debtMap.put(debtor, debtMap.get(debtor) + settledAmount);
                debtMap.put(creditor, debtMap.get(creditor) - settledAmount);

                debt += settledAmount;

                if (debt == 0) {
                    break; // Debtor's debt settled
                }
            }
        }
        return debtStr;
    }


    public Database<User> getUsers() {
        return users;
    }

    public Database<Expense> getExpenses() {
        return expenses;
    }

    public Map<User, Double> getDebtMap() {
        return debtMap;
    }
}