package projectSystem.projectsObjects;

import projectSystem.MessageService;
import projectSystem.intefaces.Observer;
import projectSystem.intefaces.Subject;

/**
 * Represents Student in the projects system
 */
public class Student extends User implements Observer {
    /**
     * String Represents the id of the Student
     */
    private String id;
    /**
     * String Represents the email of the Student
     */
    private String email;
    /**
     * String Represents the phone of the Student
     */
    private String phone;
    /**
     * Boolean Represents whether the students want to be notified by email or not
     */
    private boolean sendNotificationByEmail;
    /**
     * Boolean Represents whether the students want to be notified by sms or not
     */
    private boolean sendNotificationBySMS;

    /**
     * Default Constructor
     *
     * @param userName String represents the UserName of the Student
     * @param password String represents the password of the Student
     * @param id       String represents the id of the student
     */
    public Student(String userName, String password, String id) {
        super(userName, password);
        this.id = id;
        this.sendNotificationByEmail = false;
        this.sendNotificationBySMS = false;
        this.email = "";
        this.phone = "";
    }

    //region Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public boolean isSendNotificationByEmail() {
        return sendNotificationByEmail;
    }

    public void setSendNotificationByEmail(boolean sendNotificationByEmail) {
        this.sendNotificationByEmail = sendNotificationByEmail;
    }

    public boolean isSendNotificationBySMS() {
        return sendNotificationBySMS;
    }

    public void setSendNotificationBySMS(boolean sendNotificationBySMS) {
        this.sendNotificationBySMS = sendNotificationBySMS;
    }

    //endregion Getters and Setters


    /**
     * React and update the current object state (if necessary) after it's notified by the Subject it was listening to.
     *
     * @param changedSubject Subject that was Changed
     */
    @Override
    public void update(Subject changedSubject) {
        if (changedSubject instanceof Project) {
            Project p = (Project) changedSubject;
            if (p.getStatus() == Project.ProjectStatus.APPROVED) {
                MessageService.sendMessage(this, p);
            }
        }
    }


}
