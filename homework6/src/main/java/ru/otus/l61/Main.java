package ru.otus.l61;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();

        atm.deposit(200, 100, 100, 150);
        atm.getBalance();
        atm.printRemainingBanknotes();

        atm.withdraw(13);
        atm.getBalance();
        atm.printRemainingBanknotes();
    }
}
