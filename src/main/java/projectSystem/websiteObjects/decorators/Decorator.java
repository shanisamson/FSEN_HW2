package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;

public abstract class Decorator implements Component {
    protected Component wrappee;
    protected boolean isLimited;

    public Decorator(Component wrappee,boolean isLimited) {
        this.wrappee = wrappee;
        this.isLimited = isLimited;
    }

    @Override
    public void show(String userId) {
        wrappee.show(userId);
        if(isAllowed(this.isLimited,userId)){
            drawComponent();
        }
        else{
            //not printing becuase the user is not allowed to see this component
        }

    }

    @Override
    public boolean isAllowed(boolean isLimited, String userId) {
        return this.wrappee.isAllowed(isLimited, userId);
    }

    public abstract void drawComponent();
}
