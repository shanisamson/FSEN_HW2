package projectSystem;

import projectSystem.intefaces.Observer;
import projectSystem.intefaces.Subject;
import projectSystem.projectsObjects.Project;

import java.time.LocalDate;
import java.util.*;

/**
 * Class Responsible on all the registration of projects, and users, and hold all the data of the Projects System.
 */
public class DataManager implements Observer {
    /**
     * Map of Students users to their passwords
     */
    private Map<String, String> registeredStudents;
    /**
     * Map of all the Advisors to their passwords
     */
    private Map<String, String> registeredAdvisors;
    /**
     * Map Of all the projects id to their Projects
     */
    private Map<Integer, Project> registeredProjects;

    /**
     * List of approved Projects to show when Students tries to register tro project
     */
    private List<Project> approvedProjects;


    /**
     * Default Constructor
     */
    public DataManager() {
        this.registeredAdvisors = new HashMap<>();
        this.registeredStudents = new HashMap<>();
        this.registeredProjects = new HashMap<>();
        this.approvedProjects = new Vector<>();
    }

    //region Getters And Setters
    public Map<String, String> getRegisteredStudents() {
        return registeredStudents;
    }

    public Map<String, String> getRegisteredAdvisors() {
        return registeredAdvisors;
    }

    //endregion Getters And Setters

    /**
     * Adding student to the Map of registered Students
     *
     * @param name     String represents the Username of the student
     * @param password String represents the password of the student
     */
    public void addStudent(String name, String password) {
        addToMapIfPossible(this.registeredStudents, name, password);
    }


    /**
     * Adding advisor to the Map of registered advisors
     *
     * @param name     String represents the Username of the advisor
     * @param password String represents the password of the advisor
     */
    public void addTechnicalAdvisor(String name, String password) {
        addToMapIfPossible(this.registeredAdvisors, name, password);
    }

    /**
     * General function to add user to map if possible
     *
     * @param map   The map of Strings to Strings that to insert the key and value to
     * @param key   String Represents the key that needs to be inserted
     * @param value String Represents the Key that needs to be inserted
     * @return 'true' if the addition was successful,
     * 'false' if the given key and value already existed in the map, or if they were empty or null
     */
    private boolean addToMapIfPossible(Map<String, String> map, String key, String value) {
        if (!checkValidField(key) || !checkValidField(value)) {
            return false; // these fields cannot be empty
        }
        if (map.containsKey(key)) {
            return false; // student already exists
        }
        map.put(key, value);
        return true;
    }

    /**
     * Check if a given String is Valid
     *
     * @param field String represents a Field to be checked
     * @return 'true' if the field is not empty or null, 'false' otherwise.
     */
    private boolean checkValidField(String field) {
        return field != null && !field.isEmpty();
    }

    private boolean checkRegistered(Map<String, String> map, String key, String password) {

        if (!checkValidField(key) || !checkValidField(password)) {
            //if key or value are not valid --> return false
            return false;
        }
        if (map.containsKey(key)) {
            //if the key is in the map --> check if the password match the value
            return password.equals(map.get(key));
        } else {
            //if the given key is not in the map --> return false.
            return false;
        }
    }

    /**
     * Add project to the list of projects.
     *
     * @param user          String represents the suggester's username
     * @param password      String represents the suggester's password
     * @param firstName     String represents the first name of the suggester
     * @param lastName      String represents the last name of the suggester
     * @param phone         String represents the phone of the suggester
     * @param email         String represents the email of the suggester
     * @param organization  String represents the organization of the suggester
     * @param projectName   String represents the name of this project
     * @param description   String represents the general description of the project
     * @param numberOfHours Integer represent the number of hours that s needed for this project
     * @return unique positive number if the project was added successfully, '0' otherwise.
     */
    public int addProject(String user, String password, String firstName, String lastName,
                          String phone, String email, String organization, String projectName,
                          String description, int numberOfHours) {

        if (!checkRegistered(this.registeredStudents, user, password) && !checkRegistered(this.registeredAdvisors, user, password)) {
            return 0; // not registered user
        } else if (!checkValidField(firstName) || !checkValidField(lastName) || !checkValidField(phone) ||
                !checkValidField(email) || !checkValidField(projectName)) {
            return 0; // invalid fields for project
        } else if (numberOfHours < 200 || numberOfHours > 300) {
            return 0; // invalid number of hours for yearly project
        } else if (isSameProjectNameOfSameUserOrOrganizationInSameYear(projectName, organization, firstName, lastName)) {
            return 0; // same project in the same calendar year from same organization or user is not allowed
        } else {
            //all data is valid --> adding the project to the system
            Project toAdd = new Project(firstName, lastName, phone, email, organization, projectName, description, numberOfHours);
            this.registeredProjects.put(toAdd.getProjectId(), toAdd);
            return toAdd.getProjectId();
        }
    }

    /**
     * Checking if a given project with the same name from the same suggester or organization was already suggested in this calendar year.
     *
     * @param projectName  String represents the name of the project that needs to be checked.
     * @param organization String represents the name of organization of the project
     * @param firstName    String represents First name of the suggester
     * @param lastName     String represents  last name of the suggester
     * @return 'true' if the same project name is already exists from the same suggester or organization this year, 'false' other wise
     */
    private boolean isSameProjectNameOfSameUserOrOrganizationInSameYear(String projectName, String organization, String firstName, String lastName) {
        int currentYear = LocalDate.now().getYear();
        boolean isOrganisationEmpty = !checkValidField(organization);

        for (Project p : this.registeredProjects.values()) {
            // if the projects names are the same AND
            if (p.getProjectName().equals(projectName) &&
                    //if the first AND the last name of the suggester is the same OR
                    ((p.getSuggesterFirstName().equals(firstName) && (p.getSuggesterLastName().equals(lastName))) ||
                            //if the organization name is not empty or null AND it's equal to the this project organization name
                            (!isOrganisationEmpty && (p.getOrganization() != null) && (p.getOrganization().equals(organization))))) {
                if (currentYear == p.getSuggestionDate().getYear()) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Register students to a certain project if possible
     * @param user              String represents the username.
     * @param password          String represents the username.
     * @param projectId         Integer Represents the code-number of the wanted project.
     * @param studentList       List of Students id's(Strings) represents all the students participating in the project.
     * @param academicAdviser   String represents the name of the academic advisor.
     * @return    return the project id of the wanted project if the procedure was successful, '0' otherwise
     */
    public int registerProject(String user, String password,
                               int projectId, ArrayList<String> studentList, String academicAdviser) {

        if (!checkRegistered(this.registeredStudents, user, password)) {
            return 0; // not registered user
        } else if (!checkValidField(academicAdviser)) {
            return 0; //invalid advisor for project
        } else if (studentList.size() < 2)
            return 0; //need at least two students in the project

        Project project = registeredProjects.get(projectId);

        if (project.getAcademicAdviser() != null) {
            return 0; //the student cannot change academic adviser
        }

        if (project.getStudentList().size() > 0) {
            return 0; // other students already registered to this project
        }

        project.setAcademicAdviser(academicAdviser);
        for (String id : studentList) {
            if (!project.addStudent(id)) {
                project.clearStudentsList();
                return 0; // student registered twice to the same project
            }
        }
        return projectId;

    }

    /**
     *  If the Given Subject(Project) is Approved, and does not exists in the list of approvved Project --> adds it
     *  the Update is occurs only if it's notified by the Subject(Project) it was listening to.
     * @param changedSubject        Subject that was Changed
     */
    @Override
    public void update(Subject changedSubject) {
        if (changedSubject instanceof Project) {
            Project p = (Project) changedSubject;
            if (p.getStatus()== Project.ProjectStatus.APPROVED &&!this.approvedProjects.contains(p)) {
                this.approvedProjects.add(p);
            }
        }
    }


}
