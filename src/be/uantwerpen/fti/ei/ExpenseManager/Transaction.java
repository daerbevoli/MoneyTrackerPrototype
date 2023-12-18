package be.uantwerpen.fti.ei.ExpenseManager;

import be.uantwerpen.fti.ei.User;

public class Transaction {
    User debtor;
    User creditor;
    double amount;

    public Transaction(User debtor, User creditor, double amount) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }

    public User getDebtor() {
        return debtor;
    }

    public void setDebtor(User debtor) {
        this.debtor = debtor;
    }

    public User getCreditor() {
        return creditor;
    }

    public void setCreditor(User creditor) {
        this.creditor = creditor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "debtor=" + debtor +
                ", creditor=" + creditor +
                ", amount=" + amount +
                '}';
    }
}
