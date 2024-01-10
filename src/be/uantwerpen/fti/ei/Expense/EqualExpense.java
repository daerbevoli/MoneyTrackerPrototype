package be.uantwerpen.fti.ei.Expense;

import be.uantwerpen.fti.ei.Split.EqualSplit;
import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(String name, double amount, User paidBy, List<Split> splits, String expenseType) {
        super(name, amount, paidBy, splits, expenseType);
    }

    @Override
    public boolean validate() {
        // if any split in splits is not an equal split return false
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }

        return true;
    }
}