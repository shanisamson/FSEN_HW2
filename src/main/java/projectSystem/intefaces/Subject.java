package projectSystem.intefaces;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notify_all();
}
