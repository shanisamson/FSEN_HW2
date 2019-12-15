package projectSystem.intefaces;

public abstract class BaseDecorator {
    private Component wrappee;

    public BaseDecorator(Component wrappee) {
        this.wrappee = wrappee;
    }

    public abstract void execute();
}
