package ru.otus.l101.dbService.dao;

import ru.otus.l101.base.dataSets.UserDataSet;

public interface UserDAO {
    void save(UserDataSet user);

    UserDataSet read(long id);
}
