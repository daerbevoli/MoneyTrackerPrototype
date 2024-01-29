package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.SubjectObservers.Observer;
import be.uantwerpen.fti.ei.User;

import java.util.*;

/**
 * The user database stores all the users. Users can be added and removed under certain conditions.
 * It employs the singleton design pattern so that the database cannot be instantiated multiple times.
 */
public class UserDB extends Database<User> {

    private static UserDB firstInstance = null;

    private final List<User> db;
    private final List<Observer> observers;

    private UserDB(){
        this.db = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // singleton design pattern
    public static UserDB getInstance(){
        if (firstInstance == null){
            firstInstance = new UserDB();
        }
        return firstInstance;
    }


    @Override
    public void addEntry(User user) {
        db.add(user);
        // Notify observers
        notifyObserver();
    }

    @Override
    public void removeEntry(User user) {
        db.removeIf(element -> element.equals(user)); // safely remove item from list
    }


    @Override
    public boolean isEmpty() {
        return db.isEmpty();
    }

    @Override
    public List<User> getData() {
        return db;
    }

    // observer design pattern
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
