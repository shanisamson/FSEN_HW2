package bridgeProject;

import acptTests.auxiliary.DBRegisteredProjectInfo;
import acptTests.auxiliary.DBSuggestedProjectInfo;
import projectSystem.DataManager;


public class BridgeReal implements BridgeProject {

    private DataManager dataManager;

    public BridgeReal() {
        this.dataManager = new DataManager();
    }

    @Override
    public void registerNewTechnicalAdviser(String user, String password) {
        dataManager.addTechnicalAdvisor(user,password);
    }

    @Override
    public void addNewStudent(String user, String password) {
        dataManager.addStudent(user, password);

    }

    @Override
    public int addNewProject(String user, String pass, DBSuggestedProjectInfo suggestedProject) {
        return dataManager.addProject(user, pass, suggestedProject.firstName, suggestedProject.lastName, suggestedProject.phone,
                suggestedProject.email, suggestedProject.organization, suggestedProject.projectName, suggestedProject.description, suggestedProject.numberOfHours);
    }

    @Override
    public int registerToProject(String user, String pass, DBRegisteredProjectInfo registeredProject) {
        return dataManager.registerProject(user, pass, registeredProject.projectId, registeredProject.studentList, registeredProject.academicAdviser);
    }
}
