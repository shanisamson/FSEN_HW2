package projectsObjects;

public class Advisor extends User {
    private String name;
    public Advisor(String userName, String password) {
        super(userName, password);
    }

    public String getName() {
        return name;
    }
}
