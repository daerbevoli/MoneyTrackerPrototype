package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.SubjectObservers.Observer;
import be.uantwerpen.fti.ei.SubjectObservers.Subject;
import be.uantwerpen.fti.ei.User;

import java.util.List;

public abstract class Database<T> implements Subject {

    public abstract void addEntry(T entry);

    public abstract void removeEntry(T entry);

    // used by both dbs to print the element from the db
    public abstract void printDb();

    // check whether the database is empty
    public abstract boolean isEmpty();

    public abstract List<T> getData();

    // Observer design pattern
    public abstract void register(Observer o);

    public abstract void unregister(Observer o);

    public abstract void notifyObserver();
}
