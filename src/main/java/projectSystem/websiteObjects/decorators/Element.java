package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;

public class Element extends Decorator {

    public enum ElementType{
        AUDIO_FILE,
        LOGO,
        URL;
        public String toString(){
            switch(this){
                case AUDIO_FILE: return "Audio file";
                case LOGO: return "Logo";
                case URL: return "URL link";

            }
            return "UNKNOWN";
        }
    }

    private String fileName;
    private ElementType type;


    public Element(Component wrappee, boolean isLimited, String fileName, ElementType type) {
        super(wrappee, isLimited);
        this.fileName = fileName;
        this.type = type;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    @Override
    public void drawComponent() {
        switch (type){
            case URL:
                System.out.println(String.format("File \"%s\" may be downloaded.",this.fileName));
                break;
            case LOGO:
                System.out.println(String.format("Logo of file \"%s\" is displayed.",this.fileName));
                break;
            case AUDIO_FILE:
                System.out.println(String.format("Music of file \"%s\" is played.",this.fileName));
                break;
        }
    }

}
