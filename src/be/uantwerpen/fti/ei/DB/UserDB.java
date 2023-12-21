package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.SubjectObservers.Observer;
import be.uantwerpen.fti.ei.User;

import java.util.*;

public class UserDB extends Database {

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
    public void printDb(){
        for (User user : db){
            System.out.println(user.getName());
        }
    }

    @Override
    public boolean isEmpty() {
        return db.isEmpty();
    }

    @Override
    public List<User> getData() {
        return db;
    }

    // empty method, see database class
    @Override
    public void addExpense(Expense expense) {

    }

    @Override
    public void addUser(User user) {
        db.add(user);
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
