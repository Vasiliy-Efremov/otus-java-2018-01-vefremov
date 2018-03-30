package ru.otus.l71;

import static ru.otus.l71.NominalValues.*;

public class Main {
    public static void main(String[] args) {

        ATMDepartment atmDepartment = new ATMDepartment();
        ATM atm = new ATM();
        atm.deposit(ONE, 200);
        atm.deposit(THREE, 200);
        atm.deposit(FIVE, 200);
        atm.deposit(TEN, 200);

        atmDepartment.addToDepartment(atm);

        atm.getBalance();
        atm.withdraw(200);
        atm.getBalance();

        ATM atm2 = new ATM();
        atm2.deposit(ONE, 100);
        atm2.deposit(THREE, 100);
        atm2.deposit(FIVE, 100);
        atm2.deposit(TEN, 100);

        atmDepartment.addToDepartment(atm2);
        atm2.getBalance();
        atm2.withdraw(200);

        atmDepartment.getDepartmentBalance();
        atmDepartment.restoreAllATM();
        atmDepartment.getDepartmentBalance();
        System.out.println(atm.getBalanceWithoutMessage() + atm2.getBalanceWithoutMessage());
    }
}
