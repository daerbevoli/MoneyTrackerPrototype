package be.uantwerpen.fti.ei.SubjectObservers;

/**
 * The user Observer class observes the user database and notifies when a user is added to the database.
 */
public class userObs implements Observer{
    public userObs(){

    }

    @Override
    public void update() {
        System.out.println("User added to database");
        System.out.flush();
    }
}
