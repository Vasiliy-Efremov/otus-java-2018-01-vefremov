package ru.otus.l61;

import static ru.otus.l61.NominalValues.*;


public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.getBalance();

        atm.deposit(ONE, 200);
        atm.deposit(THREE, 200);
        atm.deposit(FIVE, 200);
        atm.deposit(TEN, 200);
        atm.printRemainingBanknotes();
        atm.getBalance();

        atm.withdraw(114);
        atm.getBalance();
    }
}
