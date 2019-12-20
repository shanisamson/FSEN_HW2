package projectSystem.projectsObjects;

import projectSystem.intefaces.Observer;
import projectSystem.intefaces.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Represents a Project in the Projects system.
 */
public class Project implements Subject {

    /**
     * Represents the max number of students that can be registered to the project.
     */
    public static final int MAX_NUMBER_OF_STUDENTS = 4;

    /**
     * Enum represents all the possible statuses of a Project
     */
    public enum ProjectStatus {
        IN_VALIDATION_PROCESS,
        APPROVED,
        REJECTED,
        IN_PROGRESS
    }

    /**
     * Integer Represents the current number of Project , and also represents the next project code  -1
     */
    private static volatile int currentProjectCode = 0;

    /**
     * generate a new Project code, and increment the CurrentProjectCode.
     *
     * @return Integer represents the current Project Code.
     */
    private synchronized static int generateNewProjectId() {
        currentProjectCode++;
        return currentProjectCode;
    }

    //region Fields
    /**
     * String represents the first name of the suggester
     */
    private String suggesterFirstName;

    /**
     * String represents the last name of the suggester
     */
    private String suggesterLastName;

    /**
     * String represents the phone of the suggester
     */
    private String phone;

    /**
     * String represents the email of the suggester
     */
    private String email;

    /**
     * String represents the organization of the suggester
     */
    private String organization;

    /**
     * String represents the project name the suggester suggest
     */
    private String projectName;

    /**
     * String represents the description  of the project
     */
    private String description;

    /**
     * Integer represents the number of hours needed for the project
     */
    private int numberOfHours;

    /**
     * Integer represents the Project Id
     */
    private int projectId;

    /**
     * List of Strings represents the Id's of all the students that are registered to this project.
     */
    private ArrayList<String> studentList;

    /**
     * String represents the name of the academic advisor
     */
    private String academicAdviser;

    /**
     * LocalDate represents the date the Project was suggested
     */
    private LocalDate suggestionDate;

    /**
     * ProjectStatus represents the current status of the project
     */
    private ProjectStatus status;

    /**
     * String represents the first name of the suggester
     */
    private String descriptionUrl;

    /**
     * List of observers (data manager and student) that needs to be notified when the status of this project is changed.
     */
    private List<Observer> _observers;

    /**
     * Represents List of students that can see all the advance info in the website of the project.
     */
    private List<String> advanceWebsiteInfoAllowedStudents;

    //endregion Fields


    /**
     * Default constructor
     *
     * @param suggesterFirstName String represents the first name of the suggester
     * @param suggesterLastName  String represents the last name of the suggester
     * @param phone              String represents the phone of the suggester
     * @param email              String represents the email of the suggester
     * @param organization       String represents the organization of the suggester
     * @param projectName        String represents the name of this project
     * @param description        String represents the general description of the project
     * @param numberOfHours      Integer represent the number of hours that s needed for this project
     */
    public Project(String suggesterFirstName, String suggesterLastName, String phone,
                   String email, String organization, String projectName,
                   String description, int numberOfHours) {
        this.suggesterFirstName = suggesterFirstName;
        this.suggesterLastName = suggesterLastName;
        this.phone = phone;
        this.email = email;
        this.organization = organization;
        this.projectName = projectName;
        this.description = description;
        this.numberOfHours = numberOfHours;
        this.projectId = generateNewProjectId();
        this.studentList = new ArrayList<>();
        this.academicAdviser = null;
        this.suggestionDate = LocalDate.now();
        this.status = ProjectStatus.IN_VALIDATION_PROCESS;
        this._observers = new Vector<>();
        this.advanceWebsiteInfoAllowedStudents = new Vector<>();
    }

    //region Getters and Setters
    public String getDescriptionUrl() {
        return descriptionUrl;
    }

    public void setDescriptionUrl(String descriptionUrl) {
        this.descriptionUrl = descriptionUrl;
    }


    public static int getCurrentProjectCode() {
        return currentProjectCode;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
        this.notify_all(); // notifying all students about the status change.
    }

    public List<String> getAdvanceWebsiteInfoAllowedStudents() {
        return advanceWebsiteInfoAllowedStudents;
    }

    public String getSuggesterFirstName() {
        return suggesterFirstName;
    }

    public String getSuggesterLastName() {
        return suggesterLastName;
    }

    public LocalDate getSuggestionDate() {
        return suggestionDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public int getProjectId() {
        return projectId;
    }
    //endregion Getters and Setters


    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public String getAcademicAdviser() {
        return academicAdviser;
    }

    public void setAcademicAdviser(String academicAdviser) {
        this.academicAdviser = academicAdviser;
    }

    /**
     * remove a given student from the registered students list of the project
     *
     * @param toRemoveId String represents the Id to remove.
     * @return 'true' if the student was removed, 'false' otherwise
     */
    public boolean removeStudent(String toRemoveId) {
        if (this.studentList.contains(toRemoveId)) {
            this.studentList.remove(toRemoveId);
            this.advanceWebsiteInfoAllowedStudents.remove(toRemoveId);
            return true;
        }
        return false;
    }

    /**
     * Adding Student to the registered students List
     *
     * @param toAdd String represents the ID of the new Student
     * @return 'true' if the student was added successfully, 'false' otherwise.
     */
    public boolean addStudent(String toAdd) {
        if (!this.studentList.contains(toAdd) || this.studentList.size() >= MAX_NUMBER_OF_STUDENTS) {
            this.studentList.add(toAdd);
            this.advanceWebsiteInfoAllowedStudents.add(toAdd);
            return true;
        }
        return false;
    }


    /**
     * Add the given id to the list of id that can see advance info in the project website.
     *
     * @param toAdd String represents the id of the student to add to the list.
     */
    public void addAdvanceInfoStudent(String toAdd) {
        this.advanceWebsiteInfoAllowedStudents.add(toAdd);
    }

    /**
     * Remove the given id from the list of id that can see advance info in the project website
     *
     * @param toRemove String represents the id of the Student to remove from the list
     */
    public void removeAdvanceInfoStudent(String toRemove) {
        this.advanceWebsiteInfoAllowedStudents.remove(toRemove);
    }

    /**
     * Removing all the registered students from the list.
     */
    public void clearStudentsList() {
        this.studentList.clear();
    }

    /**
     * Adding the given Observer to the list of observers to notify when subject is changing
     *
     * @param o Observer to add to the list of objects to notify.
     */
    @Override
    public void attach(Observer o) {
        if (!this._observers.contains(o)) {
            this._observers.add(o);
        }
    }

    /**
     * Removes the given Observer from the list of objects to notify.
     *
     * @param o Observer to remove from the list of objects to notify.
     */
    @Override
    public void detach(Observer o) {
        this._observers.remove(o);
    }

    /**
     * Notify all the Observers on a certain change in this subject.
     */
    @Override
    public void notify_all() {
        for (Observer o : _observers) {
            o.update(this);
        }
    }
}

