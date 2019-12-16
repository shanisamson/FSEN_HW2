package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;

public class Style extends Decorator {

    public enum Theme{
        DARK,
        LIGHT,
        BRAZIL,
        LAVENDER;

        public String toString(){
            switch(this){
                case DARK: return "Dark";
                case LIGHT: return "Light";
                case BRAZIL: return "Brazil";
                case LAVENDER: return "Lavender";

            }
            return "UNKNOWN";
        }
    }

    private Theme theme;

    public Style(Component wrappee, boolean isLimited, Theme theme) {
        super(wrappee, isLimited);
        this.theme = theme;
    }
    @Override
    public void drawComponent() {
        System.out.println(String.format("This WebSite is using the %s Style theme.",this.theme.toString()));
    }
}
