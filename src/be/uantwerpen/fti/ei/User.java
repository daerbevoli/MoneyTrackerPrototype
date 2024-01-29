package be.uantwerpen.fti.ei;

/**
 * The user class.
 * It implements the comparable interface to be compatible with the debtMap in the expenseManager.
 */
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
