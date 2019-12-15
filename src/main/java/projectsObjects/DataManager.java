package projectsObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private Map<String, String> registeredStudents;
    private Map<String, String> registeredAdvisors;
    private Map<Integer, Project> registeredProjects;


    public DataManager() {
        this.registeredAdvisors = new HashMap<>();
        this.registeredStudents = new HashMap<>();
        this.registeredProjects = new HashMap<>();
    }

    public Map<String, String> getRegisteredStudents() {
        return registeredStudents;
    }

    public Map<String, String> getRegisteredAdvisors() {
        return registeredAdvisors;
    }

    public void addStudent(String name, String password) {
         addToMapIfPossible(this.registeredStudents, name, password);
    }

    public void addTechnicalAdvisor(String name, String password) {
        addToMapIfPossible(this.registeredAdvisors, name, password);
    }

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

    private boolean checkValidField(String field) {
        return field != null && !field.isEmpty();
    }

    private boolean checkRegistered(Map<String,String> map, String key,String password){

        if(!checkValidField(key) || !checkValidField(password)) {
            return false;
        }
        if(map.containsKey(key)){
            return password.equals(map.get(key));
        }
        else{
            return false;
        }
    }

    public int addProject(String user, String password, String firstName, String lastName,
                           String phone, String email, String organization, String projectName,
                           String description, int numberOfHours) {

        if(!checkRegistered(this.registeredStudents,user,password) && !checkRegistered(this.registeredAdvisors,user,password)){
            return 0; // not registered user
        }
        else if (!checkValidField(firstName) || !checkValidField(lastName) || !checkValidField(phone) ||
                !checkValidField(email) || !checkValidField(projectName)){
            return 0 ; // invalid fields for project
        }
        else if(numberOfHours < 200 || numberOfHours > 300){
            return 0; // invalid number of hours for yearly project
        }

        else if(isSameProjectNameOfSameUserOrOrganizationInSameYear(projectName,organization,firstName,lastName)){
            return 0; // same project in the same calendar year from same organization or user is not allowed
        }

        else{
            //all data is valid
            Project toAdd = new Project(firstName,lastName,phone,email,organization,projectName,description,numberOfHours);
            this.registeredProjects.put(toAdd.getProjectId(),toAdd);
            return toAdd.getProjectId();
        }
    }
    private boolean isSameProjectNameOfSameUserOrOrganizationInSameYear(String projectName,String organization,String firstName,String lastName){
        int currentYear = LocalDate.now().getYear();
        boolean isOrganisationEmpty = !checkValidField(organization);
        for(Project p : this.registeredProjects.values()){
            if(p.getProjectName().equals(projectName) &&
                    ((p.getSuggesterFirstName().equals(firstName)&&(p.getSuggesterLastName().equals(lastName))) ||
                            (!isOrganisationEmpty&&(p.getOrganization()!=null)&&(p.getOrganization().equals(organization))))){
                if(currentYear == p.getSuggestionDate().getYear()){
                    return true;
                }

            }
        }
        return false;
    }

    public int registerProject(String user , String password ,
                               int projectId , ArrayList<String> studentList, String academicAdviser){

        if(!checkRegistered(this.registeredStudents,user,password)){
            return 0; // not registered user
        }

        else if (!checkValidField(academicAdviser)){
            return 0; //invalid advisor for project
        }

        else if(studentList.size() < 2)
            return 0; //need at least two students in the project

        Project project = registeredProjects.get(projectId);

        if(project.getAcademicAdviser() != null){
            return 0; //the student cannot change academic adviser
        }

        if(project.getStudentList().size() > 0){
            return 0; // other students already registered to this project
        }

        project.setAcademicAdviser(academicAdviser);
        for(String id : studentList){
            if(!project.addStudent(id)){
                project.clearStudentsList();
                return 0; // student registered twice to the same project
            }
        }
        return projectId;

    }



}
