package ru.otus.l121.dbService.dao;

import org.hibernate.Session;
import ru.otus.l121.base.dataSets.UserDataSet;

public class UserDAOImpl implements UserDAO {
    private Session session;

    public UserDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void save(UserDataSet dataSet) {
        session.save(dataSet);
    }

    @Override
    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }
}
