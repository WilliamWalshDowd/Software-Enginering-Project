public class Stack<T> {
    public StackVar<T> topOfStack;

    public Stack() {
    }

    public void push(T item) {
        StackVar<T> newTop = new StackVar<T>(item);
        newTop.nextVar = topOfStack;
        topOfStack = newTop;
    }

    public T safePop() {
        return topOfStack.getVar();
    }

    public T pop() {
        T val = topOfStack.getVar();
        topOfStack.nextVar = topOfStack;
        return val;
    }

    public boolean isEmpty() {
        if (topOfStack == null) {
            return true;
        }
        return false;
    }
}
