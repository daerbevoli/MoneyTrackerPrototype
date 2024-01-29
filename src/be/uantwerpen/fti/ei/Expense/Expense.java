package be.uantwerpen.fti.ei.Expense;

import be.uantwerpen.fti.ei.Split.Split;
import be.uantwerpen.fti.ei.User;

import java.util.List;

/**
 * The abstract expense class.
 * The validate method is implemented in its subclasses specific to their use.
 */
public abstract class Expense {
    private String name;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private String expenseType;

    public Expense(String name, double amount, User paidBy, List<Split> splits, String expenseType) {
        this.name = name;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseType = expenseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public abstract boolean validate();
}