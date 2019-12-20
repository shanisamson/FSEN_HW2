package projectSystem.intefaces;

/**
 * Part of the Decorator Pattern for the WebSite design. represent a component in the website
 */
public interface Component {
    /**
     * Show visuals of the component, if possible.
     *
     * @param userId String represents the user ID , to decide whether to show this component or not.
     */
    void show(String userId);

    /**
     * Check if the given user is allowed to see the current Component.
     *
     * @param isLimited Boolean to decide whether this component is limited to "Advance Info Users" only.
     *                  'true' -> the component is limited, 'false' -> open to everyone
     * @param userId    String represents the id of the user that want to see the component.
     * @return 'true' if the given user can see the component, 'false' otherwise.
     */
    boolean isAllowed(boolean isLimited, String userId);
}
