package ru.otus.l121.base;

import ru.otus.l121.base.dataSets.UserDataSet;

public interface DBService {

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    void shutdown();

    void getCacheData();

}
