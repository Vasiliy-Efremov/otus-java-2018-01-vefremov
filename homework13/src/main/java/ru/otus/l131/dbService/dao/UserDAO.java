package ru.otus.l131.dbService.dao;

import ru.otus.l131.base.dataSets.UserDataSet;

public interface UserDAO {
    void save(UserDataSet user);

    UserDataSet read(long id);
}
