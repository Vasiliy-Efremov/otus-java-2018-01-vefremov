package ru.otus.l111.main;

import ru.otus.l111.Cache.CacheEngineImpl;
import ru.otus.l111.base.DBService;
import ru.otus.l111.base.dataSets.AddressDataSet;
import ru.otus.l111.base.dataSets.DataSet;
import ru.otus.l111.base.dataSets.UserDataSet;
import ru.otus.l111.dbService.DBServiceImpl;

public class Main {
    public static void main(String[] args) {
        CacheEngineImpl<Long, DataSet> cacheEngine = new CacheEngineImpl<>(2);
        DBService dbService = new DBServiceImpl(cacheEngine);

        dbService.save(new UserDataSet("Иван", 28, new AddressDataSet("Троицкая")));
        dbService.save(new UserDataSet("Роман", 31, new AddressDataSet("Комитетская")));
        dbService.save(new UserDataSet("Александр", 29, new AddressDataSet("Марата")));

        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);
        UserDataSet dataSet2 = dbService.read(2);
        System.out.println(dataSet2);

        dbService.getCacheData();
        dbService.shutdown();
    }
}