import java.time.LocalDate;
import java.util.ArrayList;

public class Project {

    public enum ProjectStatus{
        IN_VALIDATION_PROCESS, APPROVED,REJECTED,IN_PROGRESS
    }
    private static volatile int currentProjectCode = 0 ;

    private synchronized static int generateNewProjectId(){
        currentProjectCode++;
        return currentProjectCode;
    }

    private String suggesterFirstName;
    private String suggesterLastName;
    private String phone;
    private String email;
    private String organization;
    private String projectName;
    private String description;
    private int numberOfHours;
    private int projectId;
    private ArrayList<String> studentList;
    private String academicAdviser;
    private LocalDate suggestionDate;
    private ProjectStatus status;

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
    }

    public static int getCurrentProjectCode() {
        return currentProjectCode;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
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

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public String getAcademicAdviser() {
        return academicAdviser;
    }

    public void setAcademicAdviser(String academicAdviser) {
        this.academicAdviser = academicAdviser;
    }
    public boolean removeStudent(String toRemoveId){
        if(this.studentList.contains(toRemoveId)){
            this.studentList.remove(toRemoveId);
            return true;
        }
        return false;
    }

    public boolean addStudent(String toAdd){
        if(this.studentList.contains(toAdd)){
            this.studentList.add(toAdd);
            return true;
        }
        return false;
    }
}

