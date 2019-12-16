package projectSystem.intefaces;

/**
 * Part of the Observer pattern.
 * Represents classes that needs to be updated when the Subject they observe on is changing.
 */
public interface Observer {
    /**
     * React and update the current object state (if necessary) after it's notified by the Subject it was listening to.
     * @param changedSubject        Subject that was Changed
     */
    void update(Subject changedSubject);
}
