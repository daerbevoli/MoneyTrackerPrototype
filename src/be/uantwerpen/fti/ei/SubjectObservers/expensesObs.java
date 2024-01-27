package be.uantwerpen.fti.ei.SubjectObservers;

public class expensesObs implements Observer{

    public expensesObs() {
    }

    @Override
    public void update() {
        System.out.println("Expense added to database");
        System.out.flush();

    }
}
