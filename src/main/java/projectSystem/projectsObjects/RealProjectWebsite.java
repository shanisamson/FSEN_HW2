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
    public boolean isLimited(String studentId) {
        return limited && (!this.project.getStudentList().contains(studentId));
    }

    @Override
    public void show() {


    }
}
