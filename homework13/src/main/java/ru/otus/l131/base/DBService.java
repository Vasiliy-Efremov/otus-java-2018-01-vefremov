package ru.otus.l131.base;

import ru.otus.l131.Cache.CacheEngine;
import ru.otus.l131.base.dataSets.DataSet;
import ru.otus.l131.base.dataSets.UserDataSet;

public interface DBService {

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    CacheEngine<Long, DataSet> getCache();

}
