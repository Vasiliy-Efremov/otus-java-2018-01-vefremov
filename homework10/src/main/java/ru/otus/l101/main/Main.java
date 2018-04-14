package ru.otus.l101.main;

import ru.otus.l101.base.DBService;
import ru.otus.l101.base.dataSets.AddressDataSet;
import ru.otus.l101.base.dataSets.UserDataSet;
import ru.otus.l101.dbService.DBServiceImpl;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBServiceImpl();

        dbService.save(new UserDataSet("Иван", 28, new AddressDataSet("Троицкая")));
        dbService.save(new UserDataSet("Роман", 31, new AddressDataSet("Комитетская")));

        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dbService.shutdown();
    }
}
