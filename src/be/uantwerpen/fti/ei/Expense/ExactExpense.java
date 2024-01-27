package be.uantwerpen.fti.ei.Expense;

import be.uantwerpen.fti.ei.Split.ExactSplit;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.List;

/**
 * The exact expense class.
 * The validate method checks whether the sum of the exact amounts equate to the total amount.
 */
public class ExactExpense extends Expense {
    public ExactExpense(String name, double amount, User paidBy, List<Split> splits, String expenseType) {
        super(name, amount, paidBy, splits, expenseType);
    }

    @Override
    public boolean validate() {
        // if any split in splits is not an exact split return false
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }
        // if the different amount does not equal the total amount paid, return false
        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }

        return totalAmount == sumSplitAmount;
    }
}