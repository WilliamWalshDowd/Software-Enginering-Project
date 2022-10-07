public class StackVar<T> {
    private T item;
    public StackVar<T> nextVar;

    public StackVar(T var) {
        item = var;
    }

    T getVar() {
        return item;
    }

    StackVar<T> nextVar() {
        return nextVar;
    }
}
