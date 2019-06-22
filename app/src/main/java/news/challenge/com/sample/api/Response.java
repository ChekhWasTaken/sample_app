package news.challenge.com.sample.api;

import java.util.Observable;

import news.challenge.com.sample.util.State;

public class Response<T> extends Observable {
    private State state;
    private T data;

    public State getState() {
        return state;
    }

    public T getData() {
        return data;
    }

    void setState(State state) {
        this.state = state;

        setChanged();
        notifyObservers();
    }

    void setData(T data) {
        this.data = data;
    }
}
