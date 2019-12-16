package projectSystem.intefaces;

/**
 * Part of the Observer Pattern . Represents Subjects that need to notify observers that observe it.
 */
public interface Subject {
    /**
     * Adding the given Observer to the list of observers to notify when subject is changing
     * @param o Observer to add to the list of objects to notify.
     */
    void attach(Observer o);

    /**
     * Removes the given Observer from the list of objects to notify.
     * @param o Observer to remove from the list of objects to notify.
     */
    void detach(Observer o);

    /**
     * Notify all the Observers on a certain change in this subject.
     */
    void notify_all();
}
