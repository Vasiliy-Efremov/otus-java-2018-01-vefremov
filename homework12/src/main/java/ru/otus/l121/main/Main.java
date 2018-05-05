package ru.otus.l121.main;

import ru.otus.l121.Cache.CacheEngine;
import ru.otus.l121.Cache.CacheEngineImpl;
import ru.otus.l121.base.DBService;
import ru.otus.l121.base.dataSets.AddressDataSet;
import ru.otus.l121.base.dataSets.DataSet;
import ru.otus.l121.base.dataSets.UserDataSet;
import ru.otus.l121.dbService.DBServiceImpl;
import ru.otus.l121.servlet.CacheServlet;
import ru.otus.l121.servlet.MyServer;

public class Main {

    public static void main(String[] args) throws Exception {
        CacheEngine<Long, DataSet> cacheEngine = new CacheEngineImpl<>(2);

        DBService dbService = new DBServiceImpl(cacheEngine);
        dbService.save(new UserDataSet("Иван", 28, new AddressDataSet("Троицкая")));
        dbService.save(new UserDataSet("Роман", 31, new AddressDataSet("Комитетская")));
        dbService.save(new UserDataSet("Александр", 29, new AddressDataSet("Марата")));

        UserDataSet dataSet = dbService.read(1);
        UserDataSet dataSet2 = dbService.read(2);
        dbService.shutdown();

        CacheServlet.setCache(cacheEngine);
        MyServer.start();
    }
}