package ru.otus.l71;

public class Memento {
    private final ATM state;

    public Memento(ATM stateToSave) {
        state = stateToSave;
    }

    public ATM getSavedState() {
        return state;
    }
}
