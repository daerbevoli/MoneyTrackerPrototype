package be.uantwerpen.fti.ei.Split;

import be.uantwerpen.fti.ei.User;

/**
 * The exact split class.
 */
public class ExactSplit extends Split {

    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}