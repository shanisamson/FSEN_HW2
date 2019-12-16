package projectSystem.projectsObjects;

import projectSystem.MessageService;
import projectSystem.intefaces.Observer;
import projectSystem.intefaces.Subject;


public class Student extends User implements Observer {
    private String id;
    private String email;
    private String phone;
    private boolean sendNotificationByEmail;
    private boolean sendNotificationBySMS;
    public Student(String userName, String password,String id) {
        super(userName, password);
        this.id = id;
        this.sendNotificationByEmail = false;
        this.sendNotificationBySMS = false;
        this.email="";
        this.phone="";
    }

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

    @Override
    public void update(Subject changedSubject) {
        if(changedSubject instanceof Project){
            Project p = (Project)changedSubject;
            if (p.getStatus() == Project.ProjectStatus.APPROVED){
                MessageService.sendMessage(this,p);
            }
        }
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
}
