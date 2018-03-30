package ru.otus.l71;

import java.util.HashMap;
import java.util.Map;

public class ATM {

    private int maxWithdrawValue = 200;
    private Map<NominalValues, Integer> allMoney = new HashMap<NominalValues, Integer>();

    public ATM() {
        for (int i = 0; i < NominalValues.values().length; i++) {
            allMoney.put(NominalValues.values()[i], 0);
        }
    }

    public ATM(ATM atm) {
        this.allMoney.putAll(atm.allMoney);
    }

    public int getBalance() {
        int balance = 0;

        for (Map.Entry<NominalValues, Integer> entry : allMoney.entrySet()) {
            balance += entry.getValue() * entry.getKey().getNominal();
        }
        System.out.println("----------------------------------");
        System.out.println("Текущий баланс банкомата: " + balance);
        System.out.println("----------------------------------");
        return balance;
    }

    public int getBalanceWithoutMessage() {
        int balance = 0;

        for (Map.Entry<NominalValues, Integer> entry : allMoney.entrySet()) {
            balance += entry.getValue() * entry.getKey().getNominal();
        }
        return balance;
    }

    public void deposit(NominalValues nominal, int count) {
        if (nominal != null && count != 0) {
            for (Map.Entry<NominalValues, Integer> entry : allMoney.entrySet()) {
                if (entry.getKey().equals(nominal)) {
                    entry.setValue(entry.getValue() + count);
                }
            }
        }
    }

    private int calculateRequiredCount(int nominal, int amountToWithdraw) {
        if (nominal == amountToWithdraw) {
            return 1;
        }

        int count = 0;
        while (amountToWithdraw / nominal > 0) {
            count++;
            amountToWithdraw -= nominal;
        }
        return count;
    }

    public void withdraw(int count) {
        System.out.println("Запрошена сумма: " + count);
        System.out.println("----------------------------------");

        if (count <= 0) {
            throw new IllegalArgumentException("Введена некорректная сумма");
        }
        if (count > maxWithdrawValue) {
            throw new IllegalArgumentException("Максимальная сумма для снятия: " + maxWithdrawValue);
        }
        if (count > getBalanceWithoutMessage()) {
            throw new IllegalArgumentException("Недостаточно денег для выдачи. Баланс банкомата: " + getBalance());
        }

        int remains = count;
        int countTenRubBanknotes = calculateRequiredCount(NominalValues.valueOf("TEN").getNominal(), remains);
        remains -= countTenRubBanknotes * NominalValues.valueOf("TEN").getNominal();
        int countFiveRubBanknotes = calculateRequiredCount(NominalValues.valueOf("FIVE").getNominal(), remains);
        remains -= countFiveRubBanknotes * NominalValues.valueOf("FIVE").getNominal();
        int countThreeRubBanknotes = calculateRequiredCount(NominalValues.valueOf("THREE").getNominal(), remains);
        remains -= countThreeRubBanknotes * NominalValues.valueOf("THREE").getNominal();
        int countOneRubBanknotes = calculateRequiredCount(NominalValues.valueOf("ONE").getNominal(), remains);

        for (Map.Entry<NominalValues, Integer> entry : allMoney.entrySet()) {
            if (entry.getKey().equals(NominalValues.valueOf("TEN"))) {
                entry.setValue(entry.getValue() - countTenRubBanknotes);
                System.out.println("Выдано купюр: " + countTenRubBanknotes + " шт, номиналом 10 рублей");
            }

            if (entry.getKey().equals(NominalValues.valueOf("FIVE"))) {
                entry.setValue(entry.getValue() - countFiveRubBanknotes);
                System.out.println("Выдано купюр: " + countFiveRubBanknotes + " шт, номиналом 5 рублей");
            }

            if (entry.getKey().equals(NominalValues.valueOf("THREE"))) {
                entry.setValue(entry.getValue() - countThreeRubBanknotes);
                System.out.println("Выдано купюр: " + countThreeRubBanknotes + " шт, номиналом 3 рубля");
            }

            if (entry.getKey().equals(NominalValues.valueOf("ONE"))) {
                entry.setValue(entry.getValue() - countOneRubBanknotes);
                System.out.println("Выдано купюр: " + countOneRubBanknotes + " шт,  купюр номиналом 1 рубль");
            }
        }
    }
}
