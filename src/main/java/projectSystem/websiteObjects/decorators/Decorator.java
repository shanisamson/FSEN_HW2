package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;

/**
 * Represents an Abstract Decorator in the Decorator design Pattern. represents a design component that can be added to the web site,
 */
public abstract class Decorator implements Component {
    /**
     * WebSite Component that is wrapped by this component
     */
    protected Component wrappee;
    /**
     * Boolean represents whether this component is blocked to some users or not
     */
    protected boolean isLimited;

    /**
     * Default constructor
     *
     * @param wrappee   Component that this Component needs to wrap.
     * @param isLimited Boolean represents whether this Component is considered "Advance Info" for users.
     */
    public Decorator(Component wrappee, boolean isLimited) {
        this.wrappee = wrappee;
        this.isLimited = isLimited;
    }

    /**
     * Show visuals of the component, if possible.
     *
     * @param userId String represents the user ID , to decide whether to show this component or not.
     */
    @Override
    public void show(String userId) {
        wrappee.show(userId);
        if (isAllowed(this.isLimited, userId)) {
            drawComponent();
        } else {
            //not printing because the user is not allowed to see this component
        }

    }

    /**
     * Check if the given user is allowed to see the current Component.
     *
     * @param isLimited Boolean to decide whether this component is limited to "registered to the project" only.
     *                  'true' means the component is limited, 'false' means open to everyone
     * @param userId    String represents the id of the user that want to see the component.
     * @return 'true' if the given user can see the component, 'false' otherwise.
     */
    @Override
    public boolean isAllowed(boolean isLimited, String userId) {
        return this.wrappee.isAllowed(isLimited, userId);
    }

    /**
     * Drawing the component in the web site. each decorator that extends this class needs to extends this Function.
     */
    public abstract void drawComponent();
}
