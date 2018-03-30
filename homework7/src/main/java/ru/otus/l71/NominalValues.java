package ru.otus.l71;

public enum NominalValues {
    ONE(1), THREE(3), FIVE(5), TEN(10);

    private int nominal;

    private NominalValues(int sum) {
        this.nominal = sum;
    }

    public int getNominal() {
        return nominal;
    }
}
