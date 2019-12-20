package projectSystem.projectsObjects;

/**
 * Represents a general user in the project system
 */
public class User {
    /**
     * String represents the username of the user
     */
    protected String userName;

    /**
     * String represents the password
     */
    protected String password;

    /**
     * Default constructor
     *
     * @param userName String represents the username of the user.
     * @param password String represents the password of the user.
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //region Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion Getters and Setters

}
