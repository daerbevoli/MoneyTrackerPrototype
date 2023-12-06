package be.uantwerpen.fti.ei.Expense;

import be.uantwerpen.fti.ei.Split.ExactSplit;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(String name, double amount, User paidBy, List<Split> splits) {
        super(name, amount, paidBy, splits);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }

        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }

        return totalAmount == sumSplitAmount;
    }
}