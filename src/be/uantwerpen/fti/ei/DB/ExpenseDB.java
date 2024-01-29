package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.SubjectObservers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The expense database stores all the expenses. Expenses can be added but not removed.
 * It employs the singleton design pattern so that the database cannot be instantiated multiple times.
 */
public class ExpenseDB extends Database<Expense> {

    private static ExpenseDB firstInstance = null;

    private final List<Expense> db;
    private final List<Observer> observers;

    private ExpenseDB(){
        this.db = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // singleton design pattern
    public static ExpenseDB getInstance(){
        if (firstInstance == null){
            firstInstance = new ExpenseDB();
        }
        return firstInstance;
    }

    @Override
    public void addEntry(Expense expense) {
        db.add(expense);
        // Notify observers
        notifyObserver();
    }

    @Override
    public boolean isEmpty() {
        return db.isEmpty();
    }

    @Override
    public List<Expense> getData() {
        return db;
    }

    // Observer design pattern
    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        int oIndex = observers.indexOf(o);
        System.out.println("Observer " + (oIndex+1) + " deleted");
        observers.remove(oIndex);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers){
            o.update();
        }
    }
}
