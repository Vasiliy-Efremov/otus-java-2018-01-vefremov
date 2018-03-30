package ru.otus.l71;

public class Originator {

    private ATM state;

    public void set(ATM state) {
        this.state = state;
    }

    public Memento saveToMemento() {
        return new Memento(this.state);
    }

    public void restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
    }

    public ATM getState() {
        return this.state;
    }
}
