package projectSystem.intefaces;

public abstract class BaseDecorator implements Component{
    private Component wrappee;

    public BaseDecorator(Component wrappee) {
        this.wrappee = wrappee;
    }

    public abstract void show();
}
