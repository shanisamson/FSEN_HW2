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
        dataManager.addTechnicalAdvisor(user, password);
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

    //region Functions For TearDown
    public void clearAllDataFromSystem() {
        this.dataManager._clearAllData();
    }

    public void removeStudentFromProject(int projectId, String studentId) {
        this.dataManager._removeStudentFromProject(projectId, studentId);
    }

    public void removeProject(int projectId) {
        this.dataManager._removeProject(projectId);
    }

    public void removeUser(String user) {
        this.dataManager._removeUser(user);
    }
    //endregion Functions For TearDown
}
