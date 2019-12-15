package projectSystem.projectsObjects;

import projectSystem.intefaces.Component;
import projectSystem.intefaces.ProjectWebsite;

public class ProxyProjectWebsite implements ProjectWebsite{

    private ProjectWebsite realWebsite;

    public ProxyProjectWebsite(ProjectWebsite real) {
        this.realWebsite = real;
    }

    public void setRealWebsite(ProjectWebsite realWebsite) {
        this.realWebsite = realWebsite;
    }

    private boolean isAllowed(String id){

        if(realWebsite == null || (this.realWebsite.isLimited(id))){
            return false;
        }
        return true;
    }

    @Override
    public void showSite(String studentId) {
        if(!isAllowed(studentId)){
            System.out.println("Access Denied");
        }
        else {
            this.realWebsite.showSite(studentId);
        }
    }



    @Override
    public boolean isLimited(String studentId) {
        return false;
    }
}
