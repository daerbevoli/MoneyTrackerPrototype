package be.uantwerpen.fti.ei;


public class User implements Comparable<User>{
    private final String name;

    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.getName());
    }
}
