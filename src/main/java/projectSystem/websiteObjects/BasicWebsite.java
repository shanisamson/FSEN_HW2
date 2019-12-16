package projectSystem.websiteObjects;

import projectSystem.intefaces.Component;
import projectSystem.projectsObjects.Project;

/**
 * Represents a Basic WebSite layout.
 */
public class BasicWebsite implements Component {
    /**
     * Project Represents the project this website is representing
     */
    private Project project;
    /**
     * Boolean represents whether this website is visible to all users or just to some of them
     */
    private boolean limited;


    /**
     * default Constructor
     * @param project           Project this website is showing
     * @param limited           Boolean represents whether this website is visible to all users or just to some of them
     */
    public BasicWebsite(Project project, boolean limited) {
        this.project = project;
        this.limited = limited;
    }

    /**
     * Show visuals of the website, if possible.
     * @param userId            String represents the user ID , to decide whether to show this component or not.
     */
    @Override
    public void show(String userId) {
        if(isAllowed(this.limited,userId)){
            System.out.println(String.format("Displaying WebSite of project %s",this.project.getProjectName()));
        }
        else{
            System.out.println("ACCESS DENIED");
        }

    }

    /**
     * Check if the given user is allowed to see the current Component.
     *
     * @param isLimited          Boolean to decide whether this component is limited to "registered to the project" only.
     *                           'true' -> the compponent is limited, 'false' -> open to everyone
     * @param userId             String represents the id of the user that want to see the component.
     * @return  'true' if the given user can see the component, 'false' otherwise.
     */
    @Override
    public boolean isAllowed(boolean isLimited, String userId) {
        if(!isLimited || project.getStudentList().contains(userId)){
            return true;
        }
        else return false;
    }
}
