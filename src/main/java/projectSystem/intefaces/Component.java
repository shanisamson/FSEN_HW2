package projectSystem.intefaces;

public interface Component {
    void show(String userId);
    boolean isAllowed(boolean isLimited,String userId);
}
