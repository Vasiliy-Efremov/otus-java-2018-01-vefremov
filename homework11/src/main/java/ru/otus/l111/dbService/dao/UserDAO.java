package ru.otus.l111.dbService.dao;

import ru.otus.l111.base.dataSets.UserDataSet;

public interface UserDAO {
    void save(UserDataSet user);

    UserDataSet read(long id);
}
