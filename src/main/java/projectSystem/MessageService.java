package projectSystem;

import projectSystem.projectsObjects.Project;
import projectSystem.projectsObjects.Student;

public class MessageService {

    public static void sendMessage(Student toSend, Project p){
        String message = String.format("Hello, you might want to know that the Project \"%s\" was approved.\n" +
                "To Project Url click here: %s",p.getProjectName(),p.getDescriptionUrl());
        if(toSend.isSendNotificationByEmail()){
            sendEmail(toSend.getEmail(),toSend.getUserName(),message);
        }
        if(toSend.isSendNotificationBySMS()){
            sendSMS(toSend.getPhone(),toSend.getUserName(),message);

        }
    }

    private static void sendSMS(String phone, String userName, String msg){
        //todo still needs to be implemented
        if(phone == null || phone.isEmpty()){
            System.out.println(String.format("Could'nt send sms since the the user \"%s\" doesn't have a phone registered in the system",userName));
        }
        else{
            System.out.println(String.format("This SMS was sent to phone number: %s of the user \"%s\" : %s",phone,userName,msg));
        }

    }
    private static void sendEmail(String email, String userName, String msg){
        //todo still needs to be implemented
        if(email == null || email.isEmpty()){
            System.out.println(String.format("Could'nt send email since the the user \"%s\" doesn't have an email registered in the system",userName));
        }
        System.out.println(String.format("This Mail was sent the email: %s of the user \"%s\" : %s",email,userName,msg));

    }
}
