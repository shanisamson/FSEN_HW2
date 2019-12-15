package projectSystem.projectsObjects;

import projectSystem.intefaces.Observer;
import projectSystem.intefaces.Subject;


public class Student extends User implements Observer {
    private String id;
    private boolean sendNotificationByEmail;
    private boolean sendNotificationBySMS;
    public Student(String userName, String password,String id) {
        super(userName, password);
        this.id = id;
        this.sendNotificationByEmail = false;
        this.sendNotificationBySMS = false;
    }

    public String getId() {
        return id;
    }

    @Override
    public void update(Subject changedSubject) {
        if(changedSubject instanceof Project){
            Project p = (Project)changedSubject;
            if (p.getStatus() == Project.ProjectStatus.APPROVED){
                String message = String.format("Hello, you might want to know that the Project \"%s\" was approved.\n" +
                        "To Project Url click here: %s",p.getProjectName(),p.getDescriptionUrl());
                if(sendNotificationByEmail){
                    //todo still needs to be implemented
                    System.out.println(String.format("This Mail was sent to the user \"%s\" : %s",this.getUserName(),message));
                }
                if(sendNotificationBySMS){
                    //todo still needs to be implemented
                    System.out.println(String.format("This SMS was sent to the user \"%s\" : %s",this.getUserName(),message));
                }
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
