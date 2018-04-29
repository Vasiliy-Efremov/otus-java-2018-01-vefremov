package ru.otus.l111.base;

import ru.otus.l111.base.dataSets.UserDataSet;

public interface DBService {

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    void shutdown();

    void getCacheData();

}
