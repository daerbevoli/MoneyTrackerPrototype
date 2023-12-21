package be.uantwerpen.fti.ei.DB;

import be.uantwerpen.fti.ei.Expense.Expense;
import be.uantwerpen.fti.ei.SubjectObservers.Observer;
import be.uantwerpen.fti.ei.User;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDB extends Database{

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
    public void addExpense(Expense expense) {
        db.add(expense);
    }

    // empty method, see database class
    @Override
    public void addUser(User user) {

    }

    // yet to add splits
    @Override
    public void printDb() {
        for (Expense expense : db){
            String paidBy = expense.getPaidBy().getName();
            System.out.println(paidBy +  " paid for " + expense.getName() + " To");
            for (int i = 0 ; i < expense.getSplits().size(); i++){
                String paidTo = expense.getSplits().get(i).getUser().getName();
                if (paidTo.equals(expense.getPaidBy().getName())){
                    paidTo = "himself";
                }
                System.out.print(paidTo + " ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean isEmpty() {
        return db.isEmpty();
    }

    @Override
    public List<User> getData() {
        return null;
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
