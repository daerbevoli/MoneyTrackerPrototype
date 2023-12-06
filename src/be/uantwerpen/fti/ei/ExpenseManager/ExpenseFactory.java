package be.uantwerpen.fti.ei.ExpenseManager;

import be.uantwerpen.fti.ei.Expense.EqualExpense;
import be.uantwerpen.fti.ei.Expense.ExactExpense;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.List;

// Factory design pattern
public class ExpenseFactory {

    public static Expense createExpense(String name, String expenseType, double amount, User paidBy, List<Split> splits) {

        switch (expenseType) {
            case "EXACT":
                return new ExactExpense(name, amount, paidBy, splits);

            case "EQUAL":
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount/totalSplits));
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                return new EqualExpense(name, amount, paidBy, splits);

            default:
                return null;
        }
    }
}
