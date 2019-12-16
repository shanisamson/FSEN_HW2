package projectSystem.websiteObjects;

import projectSystem.intefaces.Component;
import projectSystem.projectsObjects.Project;

public class BasicWebsite implements Component {
    private Project project;
    private boolean limited;


    public BasicWebsite(Project project, boolean limited) {
        this.project = project;
        this.limited = limited;
    }

    @Override
    public void show(String userId) {
        if(isAllowed(this.limited,userId)){
            System.out.println(String.format("Displaying WebSite of project %s",this.project.getProjectName()));
        }
        else{
            System.out.println("ACCESS DENIED");
        }

    }

    @Override
    public boolean isAllowed(boolean isLimited, String userId) {
        if(!isLimited || project.getStudentList().contains(userId)){
            return true;
        }
        else return false;
    }
}
