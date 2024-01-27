package be.uantwerpen.fti.ei.Split;

import be.uantwerpen.fti.ei.User;

/**
 * The abstract split class.
 * The amount field is an undefined visibility because not all subclasses inherit the property.
 */
public abstract class Split {
    private User user;
    double amount;

    public Split(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}