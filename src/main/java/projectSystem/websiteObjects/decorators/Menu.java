package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;

/**
 * Represents a menu that can be added tro the project WebSite
 */
public class Menu extends Decorator {

    /**
     * Enum to represents where in the web site the menu is displayed
     */
    public enum MenuSide{
        LEFT,
        RIGHT,
        UP;

        public String toString(){
            switch(this){
                case UP: return "upper";
                case RIGHT: return "right";
                case LEFT: return "left";

            }
            return "UNKNOWN";
        }
    }

    /**
     * Represents the side where this menu is displayed in the website.
     */
    private MenuSide menuSide;

    /**
     * Default constructor
     * @param wrappee       Component that this Component needs to wrap.
     * @param isLimited     Boolean represents whether this Component is visible or not to some users.
     * @param menuSide      MenuSide Enum represents the side where this menu is displayed in the website.
     */
    public Menu(Component wrappee, boolean isLimited, MenuSide menuSide) {
        super(wrappee, isLimited);
        this.menuSide = menuSide;
    }

    /**
     * Drawing this menu in the web site.
     */
    @Override
    public void drawComponent() {
        System.out.println(String.format("Menu is displayed on the %s side",this.menuSide.toString()));

    }

}
