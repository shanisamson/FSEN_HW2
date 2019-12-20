package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;

/**
 * Represents a Style\Theme that Can be Added to the project Website
 */
public class Style extends Decorator {

    public enum Theme {
        DARK,
        LIGHT,
        BRAZIL,
        LAVENDER;

        public String toString() {
            switch (this) {
                case DARK:
                    return "Dark";
                case LIGHT:
                    return "Light";
                case BRAZIL:
                    return "Brazil";
                case LAVENDER:
                    return "Lavender";

            }
            return "UNKNOWN";
        }
    }

    /**
     * Enum Represents the current Theme from the selection of themes
     */
    private Theme theme;

    /**
     * Default Constructor
     *
     * @param wrappee   Component that this Component needs to wrap.
     * @param isLimited Boolean represents whether this Component is visible or not to some users.
     * @param theme     Theme Enum Represents the style that was chosen by the students for this style component
     */
    public Style(Component wrappee, boolean isLimited, Theme theme) {
        super(wrappee, isLimited);
        this.theme = theme;
    }

    /**
     * Drawing the style in the web site
     */
    @Override
    public void drawComponent() {
        System.out.println(String.format("This WebSite is using the %s Style theme.", this.theme.toString()));
    }
}
