package be.uantwerpen.fti.ei.SubjectObservers;

/**
 * The expense observer class observes the expenses database and notifies when an expense is added to the database.
 */
public class expenseObs implements Observer{

    public expenseObs() {
    }

    @Override
    public void update() {
        System.out.println("Expense added to database");
        System.out.flush();

    }
}
