import java.time.LocalDate;
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

    public boolean addStudent(String name, String password) {
        return addToMapIfPossible(this.registeredStudents, name, password);

    }

    public boolean addTechnicalAdvisor(String name, String password) {
        return addToMapIfPossible(this.registeredAdvisors, name, password);
    }

    private boolean addToMapIfPossible(Map<String, String> map, String key, String value) {
        if (checkValidField(key) || checkValidField(value)) {
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
        if(map.containsKey(key)){
            return password.equals(map.get(key));
        }
        else{
            return false;
        }
    }

    private int addProject(String user, String password, String firstName, String lastName,
                           String phone, String email, String organization, String projectName,
                           String description, int numberOfHours) {

        if(!checkRegistered(this.registeredStudents,user,password) || !checkRegistered(this.registeredAdvisors,user,password)){
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



}
