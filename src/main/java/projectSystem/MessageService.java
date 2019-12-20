package projectSystem;

import projectSystem.projectsObjects.Project;
import projectSystem.projectsObjects.Student;

/**
 * Class That respoinsible to all the logic needed to send messages to the students via sms or email.
 */
public class MessageService {

    /**
     * notify a student about an approved project by Sending message via SMS and\or EMail
     *
     * @param toSend Student Object Represents the Students to send the message to.
     * @param p      Project Object to notify the student about
     */
    public static void sendMessage(Student toSend, Project p) {
        String message = String.format("Hello, you might want to know that the Project \"%s\" was approved.\n" +
                "To Project Url click here: %s", p.getProjectName(), p.getDescriptionUrl());
        if (toSend.isSendNotificationByEmail()) {
            //send via email
            sendEmail(toSend.getEmail(), toSend.getUserName(), message);
        }
        if (toSend.isSendNotificationBySMS()) {
            //send via sms
            sendSMS(toSend.getPhone(), toSend.getUserName(), message);

        }
    }

    /**
     * Sends the message via sms to the given phone number.
     *
     * @param phone    String represents the user phone number.
     * @param userName String represents the username
     * @param msg      String represents the message to send
     */
    private static void sendSMS(String phone, String userName, String msg) {
        if (phone == null || phone.isEmpty()) {
            //if there was no phone in the student profile --> prints an error
            System.out.println(String.format("Could'nt send sms since the the user \"%s\" doesn't have a phone registered in the system", userName));
        } else {
            //todo still needs to be implemented
            System.out.println(String.format("This SMS was sent to phone number: %s of the user \"%s\" : %s", phone, userName, msg));
        }

    }

    /**
     * Sends the message via sms to the given email.
     *
     * @param email    String represents the user email
     * @param userName String represents the username
     * @param msg      String represents the message to send
     */
    private static void sendEmail(String email, String userName, String msg) {
        if (email == null || email.isEmpty()) {
            System.out.println(String.format("Could'nt send email since the the user \"%s\" doesn't have an email registered in the system", userName));
        } else {
            //todo still needs to be implemented
            System.out.println(String.format("This Mail was sent to the email: \"%s\" of the user \"%s\" : \"%s\"", email, userName, msg));
        }
    }
}
