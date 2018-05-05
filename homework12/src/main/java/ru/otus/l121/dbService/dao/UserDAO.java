package ru.otus.l121.dbService.dao;

import ru.otus.l121.base.dataSets.UserDataSet;

public interface UserDAO {
    void save(UserDataSet user);

    UserDataSet read(long id);
}
