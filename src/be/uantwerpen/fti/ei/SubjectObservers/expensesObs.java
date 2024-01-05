package be.uantwerpen.fti.ei.SubjectObservers;

public class expensesObs implements Observer{


    public expensesObs() {
    }

    @Override
    public void update() {
        System.out.println("Expenses added to the database");
    }
}
