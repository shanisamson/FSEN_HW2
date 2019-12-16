package projectSystem.projectsObjects;

/**
 * Class Represents an Advisor to projects.
 */
public class Advisor extends User {
    /**
     * Represents the name of the advisor.
     */
    private String name;

    /**
     * Default constructor
     * @param userName          String represents the user name of the advisor.
     * @param password          String represents the password of the advisor.
     */
    public Advisor(String userName, String password) {
        super(userName, password);
        this.name = "";
    }

    /**
     * Another Constructor
     * @param userName          String represents the user name of the advisor.
     * @param password          String represents the password of the advisor.
     * @param name              String represents the actual name of the advisor.
     */
    public Advisor(String userName, String password, String name) {
        super(userName, password);
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
