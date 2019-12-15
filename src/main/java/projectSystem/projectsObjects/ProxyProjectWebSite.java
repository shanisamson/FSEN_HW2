package projectSystem.projectsObjects;

import projectSystem.intefaces.Component;
import projectSystem.intefaces.ProjectWebsite;

public class ProxyProjectWebSite implements ProjectWebsite, Component {

    private ProjectWebsite realWebsite;

    public ProxyProjectWebSite(ProjectWebsite real) {
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
            this.show();
        }
        else {
            this.realWebsite.showSite(studentId);
        }
    }

    @Override
    public void show() {
        System.out.println("ACCESS DENIED");
    }

    @Override
    public boolean isLimited(String id) {
        return false;
    }
}
