package hu.szohrfe.noisegenerator.observer;

import java.util.ArrayList;

public abstract class Observable {
    protected final ArrayList<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    protected abstract void notifyObservers();
}
