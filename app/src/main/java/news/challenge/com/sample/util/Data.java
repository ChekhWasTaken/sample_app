package news.challenge.com.sample.util;

public final class Data<T> {
    private final State state;
    private final T data;

    public Data(State state, T data) {
        this.state = state;
        this.data = data;
    }

    public State getState() {
        return state;
    }

    public T getData() {
        return data;
    }
}
