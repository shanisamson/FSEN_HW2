package projectSystem.websiteObjects.decorators;

import projectSystem.intefaces.Component;

/**
 * Class Represents an Element the students can add to the website.
 */
public class Element extends Decorator {

    /**
     * Enum Represents the Type of Element
     */
    public enum ElementType {
        AUDIO_FILE,
        LOGO,
        URL;

        public String toString() {
            switch (this) {
                case AUDIO_FILE:
                    return "Audio file";
                case LOGO:
                    return "Logo";
                case URL:
                    return "URL link";

            }
            return "UNKNOWN";
        }
    }

    /**
     * String Represents the fileName
     */
    private String fileName;
    /**
     * Element Type represents the type of this element.
     */
    private ElementType type;


    /**
     * Default constructor
     *
     * @param wrappee   Component this element is Wrapping.
     * @param isLimited Boolean represents whether this Element is visible or not to some users.
     * @param fileName  String represents the filename of this element.
     * @param type      Element Type Represents the file type.
     */
    public Element(Component wrappee, boolean isLimited, String fileName, ElementType type) {
        super(wrappee, isLimited);
        this.fileName = fileName;
        this.type = type;
    }


    //region Getters and Setters
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
    //endregion Getters and Setters


    /**
     * Drawing the element in the web site.
     */
    @Override
    public void drawComponent() {
        switch (type) {
            case URL:
                System.out.println(String.format("File \"%s\" may be downloaded.", this.fileName));
                break;
            case LOGO:
                System.out.println(String.format("Logo of file \"%s\" is displayed.", this.fileName));
                break;
            case AUDIO_FILE:
                System.out.println(String.format("Music of file \"%s\" is played.", this.fileName));
                break;
        }
    }

}
