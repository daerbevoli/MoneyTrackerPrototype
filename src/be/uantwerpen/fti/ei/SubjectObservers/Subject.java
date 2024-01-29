package be.uantwerpen.fti.ei.SubjectObservers;

/**
 * The subject interface with its methods.
 */
public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObserver();
}
