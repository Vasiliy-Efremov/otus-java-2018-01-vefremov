package ru.otus.l61;

import ru.otus.l61.money.Five_rub;
import ru.otus.l61.money.One_rub;
import ru.otus.l61.money.Ten_rub;
import ru.otus.l61.money.Three_rub;

public class ATM {

    private int maxWithdrawValue = 200;

    One_rub one_rub = new One_rub();
    Three_rub three_rub = new Three_rub();
    Five_rub five_rub = new Five_rub();
    Ten_rub ten_rub = new Ten_rub();

    public int getBalance() {
        int balance = 0;
        balance += one_rub.getCount() * one_rub.getNominal()
                + three_rub.getCount() * three_rub.getNominal()
                + five_rub.getCount() * five_rub.getNominal()
                + ten_rub.getCount() * ten_rub.getNominal();
        System.out.println("Текущий баланс банкомата: " + balance);
        System.out.println("----------------------------------");
        return balance;
    }

    public void printRemainingBanknotes() {
        System.out.println("Колличество банкнот по 10 рублей: " + ten_rub.getCount() + " шт");
        System.out.println("Колличество банкнот по 5 рублей: " + five_rub.getCount() + " шт");
        System.out.println("Колличество банкнот по 3 рубля: " + three_rub.getCount() + " шт");
        System.out.println("Колличество банкнот по 1 рублю: " + one_rub.getCount() + " шт");
        System.out.println("----------------------------------");
    }

    public void deposit(int countOneBanknotes, int countThreeBanknotes, int countFiveBanknotes, int countTenBanknotes) {
        this.one_rub.setCount(this.one_rub.getCount() + countOneBanknotes);
        this.three_rub.setCount(this.three_rub.getCount() + countThreeBanknotes);
        this.five_rub.setCount(this.five_rub.getCount() + countFiveBanknotes);
        this.ten_rub.setCount(this.ten_rub.getCount() + countTenBanknotes);
        int count = countOneBanknotes * one_rub.getNominal() + countThreeBanknotes * three_rub.getNominal() +
                countFiveBanknotes * five_rub.getNominal() + countTenBanknotes * ten_rub.getNominal();
        System.out.println("баланс банкомата пополнен на: " + count);
        System.out.println("----------------------------------");
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
        if (count <= 0) {
            throw new IllegalArgumentException("Введена некорректная сумма");
        }
        if (count > maxWithdrawValue) {
            throw new IllegalArgumentException("Максимальная сумма для снятия: " + maxWithdrawValue);
        }
        if (count > getBalance()) {
            throw new IllegalArgumentException("Недостаточно денег для выдачи. Баланс банкомата: " + getBalance());
        }

        int remains = count;
        int countTenRubBanknotes = calculateRequiredCount(ten_rub.getNominal(), remains);
        remains -= countTenRubBanknotes * ten_rub.getNominal();
        int countFiveRubBanknotes = calculateRequiredCount(five_rub.getNominal(), remains);
        remains -= countFiveRubBanknotes * five_rub.getNominal();
        int countThreeRubBanknotes = calculateRequiredCount(three_rub.getNominal(), remains);
        remains -= countThreeRubBanknotes * three_rub.getNominal();
        int countOneRubBanknotes = calculateRequiredCount(one_rub.getNominal(), remains);

        ten_rub.setCount(ten_rub.getCount() - countTenRubBanknotes);
        five_rub.setCount(five_rub.getCount() - countFiveRubBanknotes);
        three_rub.setCount(three_rub.getCount() - countThreeRubBanknotes);
        one_rub.setCount(one_rub.getCount() - countOneRubBanknotes);
        System.out.println("Запрошена сумма: " + count);
        System.out.println("----------------------------------");
    }
}
