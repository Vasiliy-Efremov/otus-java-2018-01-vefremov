package ru.otus.l71;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private final List<Memento> savedStates = new ArrayList<>();

    public void add(Memento state) {
        savedStates.add(state);
    }

    public Memento get(int index) {
        return savedStates.get(index);
    }

}
