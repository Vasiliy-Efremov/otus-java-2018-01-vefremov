package ru.otus.l101.base;

import ru.otus.l101.base.dataSets.UserDataSet;

public interface DBService {

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    void shutdown();

}
