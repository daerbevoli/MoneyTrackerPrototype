package be.uantwerpen.fti.ei.SubjectObservers;

public class usersObs implements Observer{

    public usersObs(){

    }

    @Override
    public void update() {
        System.out.println("User added to database");
    }
}
