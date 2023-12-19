package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.SubjectObservers.Observer;
import be.uantwerpen.fti.ei.SubjectObservers.Subject;
import be.uantwerpen.fti.ei.User;

public abstract class Database implements Subject {
    // used by user db
    public abstract void addUser(User user);

    // used by expense db
    public abstract void addExpense(Expense expense);

    // should be a way to implement this to avoid empty methods
    //public abstract void addEntry(Object obj);

    // used by both dbs to print the element from the db
    public abstract void printDb();

    // check whether the database is empty
    public abstract boolean isEmpty();

    // Observer design pattern
    public abstract void register(Observer o);

    public abstract void unregister(Observer o);

    public abstract void notifyObserver();
}
