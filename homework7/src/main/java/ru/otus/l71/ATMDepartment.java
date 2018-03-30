package ru.otus.l71;

import java.util.ArrayList;
import java.util.List;

public class ATMDepartment {

    private List<ATM> allAtms = new ArrayList<>();
    private Caretaker caretaker;
    private Originator originator;

    public ATMDepartment() {
        caretaker = new Caretaker();
        originator = new Originator();
    }

    public void addToDepartment(ATM atm) {
        allAtms.add(atm);
        originator.set(new ATM(atm));
        caretaker.add(originator.saveToMemento());
    }

    public void restoreAllATM() {
        for (int i = 0; i < allAtms.size(); i++) {
            originator.restoreFromMemento(caretaker.get(i));
            allAtms.set(i, originator.getState());
        }
        System.out.println("Восстановлено первоначальное состояние банкоматов");
        System.out.println("----------------------------------");
    }

    public void getDepartmentBalance() {
        int balance = 0;

        for (int i = 0; i < allAtms.size(); i++) {
            balance += allAtms.get(i).getBalanceWithoutMessage();
        }
        System.out.println("----------------------------------");
        System.out.println("Текущий баланс всех банкоматов: " + balance);
        System.out.println("----------------------------------");
    }
}
