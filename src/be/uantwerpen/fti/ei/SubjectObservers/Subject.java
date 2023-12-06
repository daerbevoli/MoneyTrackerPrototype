package be.uantwerpen.fti.ei.SubjectObservers;

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObserver();
}
