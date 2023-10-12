package hu.szohrfe.noisegenerator;

import java.util.ArrayList;

public abstract class Observable {
    protected final ArrayList<Observer> observers;

    Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    protected abstract void notifyObservers();
}
