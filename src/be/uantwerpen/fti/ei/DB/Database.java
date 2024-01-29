package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.SubjectObservers.Observer;
import be.uantwerpen.fti.ei.SubjectObservers.Subject;

import java.util.List;

/**
 * Abstract database class inherited by the users and expenses database.
 * The database is the model in the MVC design pattern and the subject for the observer design pattern.
 * @param <T> Type of database of the subclass.
 */
public abstract class Database<T> implements Subject {

    public abstract void addEntry(T entry);

    // not abstract because it does not get overridden in all the subclasses
    public void removeEntry(T entry){};

    public abstract boolean isEmpty();

    public abstract List<T> getData();

    // Observer design pattern
    public abstract void register(Observer o);

    public abstract void unregister(Observer o);

    public abstract void notifyObserver();
}
