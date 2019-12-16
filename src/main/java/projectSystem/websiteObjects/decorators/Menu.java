package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;


public class Menu extends Decorator {

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
    private MenuSide menuSide;

    public Menu(Component wrappee, boolean isLimited, MenuSide menuSide) {
        super(wrappee, isLimited);
        this.menuSide = menuSide;
    }

    @Override
    public void drawComponent() {
        System.out.println(String.format("Menu is displayed on the %s side",this.menuSide.toString()));

    }

    public MenuSide getMenuSide() {
        return menuSide;
    }

    public void setMenuSide(MenuSide menuSide) {
        this.menuSide = menuSide;
    }
}
