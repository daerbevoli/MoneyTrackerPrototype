package be.uantwerpen.fti.ei.ExpenseManager;

import be.uantwerpen.fti.ei.Expense.EqualExpense;
import be.uantwerpen.fti.ei.Expense.ExactExpense;
import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.List;

// Factory design pattern
public class ExpenseFactory {

    public static Expense createExpense(String name, String expenseType, double amount, User paidBy, List<Split> splits) throws Exception {

        switch (expenseType) {
            case "EXACT":
                Expense expense = new ExactExpense(name, amount, paidBy, splits, expenseType);
                if (expense.validate()){
                    return expense;
                } else {
                    throw new Exception("Exact amounts don't add up");
                }

            case "EQUAL":
                // if amount split equally, divide equally
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount/totalSplits));
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }

                // rounding may lead to discrepancy in total split amount
                // the line below adjust the amount of the first split to account for it
                splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                return new EqualExpense(name, amount, paidBy, splits, expenseType);

            default:
                return null;
        }
    }
}
