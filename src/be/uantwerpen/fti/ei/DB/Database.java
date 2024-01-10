package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.SubjectObservers.Observer;
import be.uantwerpen.fti.ei.SubjectObservers.Subject;

import java.util.List;

// database = model
public abstract class Database<T> implements Subject {

    public abstract void addEntry(T entry);

    public abstract void removeEntry(T entry);

    // check whether the database is empty
    public abstract boolean isEmpty();

    public abstract List<T> getData();

    // Observer design pattern
    public abstract void register(Observer o);

    public abstract void unregister(Observer o);

    public abstract void notifyObserver();
}
