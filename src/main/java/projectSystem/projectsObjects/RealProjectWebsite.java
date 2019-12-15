package projectSystem.projectsObjects;

import projectSystem.intefaces.Component;
import projectSystem.intefaces.ProjectWebsite;

public class RealProjectWebsite implements Component, ProjectWebsite {
    private Project project;
    private boolean limited;

    public RealProjectWebsite(Project project, boolean limited) {
        this.project = project;
        this.limited = limited;
    }

    @Override
    public void showSite(String studentId) {

    }

    @Override
    public boolean isLimited(String id) {
        return limited && (!this.project.getStudentList().contains(id));
    }

    @Override
    public void show() {


    }
}
